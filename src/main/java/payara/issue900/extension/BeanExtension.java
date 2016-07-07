
package payara.issue900.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterTypeDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

import org.apache.deltaspike.core.util.bean.BeanBuilder;

import payara.issue900.beans.AfterBeanDiscoveryBean;
import payara.issue900.beans.AfterTypeDiscoveryBean;
import payara.issue900.beans.BeforeBeanDiscoveryBean;

public class BeanExtension
implements Extension
{

	void beforeBeanDiscovery( BeanManager bm, @Observes BeforeBeanDiscovery event )
	{
		final AnnotatedType<?> type = bm.createAnnotatedType( BeforeBeanDiscoveryBean.class );

		event.addAnnotatedType( type/*, type.getJavaClass().getName() + "#"*/ );
	}

	void afterTypeDiscovery( BeanManager bm, @Observes AfterTypeDiscovery event )
	{
		final AnnotatedType<?> type = bm.createAnnotatedType( AfterTypeDiscoveryBean.class );

		event.addAnnotatedType( type, type.getJavaClass().getName() + "#" );
	}

	void afterBeanDiscovery( BeanManager bm, @Observes AfterBeanDiscovery event )
	{
		addBean( bm, event, AfterBeanDiscoveryBean.class );
	}

	private <T> void addBean( BeanManager bm, AfterBeanDiscovery event, Class<T> cls )
	{
		final AnnotatedType<T> type = bm.createAnnotatedType( cls );
		final BeanBuilder<T> bb = new BeanBuilder<>( bm );

		bb.readFromType( type );

		event.addBean( bb.create() );
	}
}
