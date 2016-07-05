
package payara.issue900.test;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import payara.issue900.beans.BusinessBean;
import payara.issue900.beans.BusinessBeanImpl;
import payara.issue900.beans.SomeBean1;
import payara.issue900.beans.SomeBean2;
import payara.issue900.extension.SomeExtension;
import payara.issue900.model.Entity1;
import payara.issue900.model.Entity2;

@RunWith( Arquillian.class )
public class EntityManagerTest
{

	@Deployment
	public static Archive<?> createDeployment() throws IOException
	{
		final String xml;

		try( InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream( "persistence-template.xml" ) ) {
			xml = IOUtils.toString( is, "UTF-8" );
		}

		// archive for persistence unit PU1
		final JavaArchive mod1 = ShrinkWrap.create( JavaArchive.class, "model1.jar" )
			.addClass( Entity1.class )
			.addAsManifestResource( new StringAsset( xml.replace( "%unitName%", "PU1" ) ), "persistence.xml" );

		// archive for persistence unit PU2
		final JavaArchive mod2 = ShrinkWrap.create( JavaArchive.class, "model2.jar" )
			.addClass( Entity2.class )
			.addAsManifestResource( new StringAsset( xml.replace( "%unitName%", "PU2" ) ), "persistence.xml" );

		// archive containing SomeBean1 and extra business
		final JavaArchive bsn = ShrinkWrap.create( JavaArchive.class, "beans.jar" )
			.addClasses( BusinessBean.class, BusinessBeanImpl.class, SomeBean1.class )
			.addAsManifestResource( new ClassLoaderAsset( "beans-annotated.xml" ), "beans.xml" );

		// the CDI extension that provides SomeBean2
		final JavaArchive ext = ShrinkWrap.create( JavaArchive.class, "extension.jar" )
			.addClasses( SomeExtension.class, SomeBean2.class )
			.addAsManifestResource( new StringAsset( SomeExtension.class.getName() ), "services/" + Extension.class.getName() );

		return ShrinkWrap.create( WebArchive.class )
			.addAsWebInfResource( new ClassLoaderAsset( "beans-annotated.xml" ), "beans.xml" )
			.addAsLibraries( mod1, mod2, ext, bsn );
	}

	@Inject
	private BusinessBean bean;

	@Test
	public void bean1_em1()
	{
		this.bean.bean1_em1();
	}

	@Test
	public void bean1_em2()
	{
		this.bean.bean1_em2();
	}

	@Test
	public void bean2_em1()
	{
		this.bean.bean2_em1();
	}

	@Test
	public void bean2_em2()
	{
		this.bean.bean2_em2();
	}
}
