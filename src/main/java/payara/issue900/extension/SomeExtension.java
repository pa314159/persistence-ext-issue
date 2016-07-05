
package payara.issue900.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterTypeDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

import payara.issue900.beans.SomeBean2;

public class SomeExtension
implements Extension
{

	void afterTypeDiscovery( BeanManager bm, @Observes AfterTypeDiscovery event )
	{
		final AnnotatedType<?> type = bm.createAnnotatedType( SomeBean2.class );

		event.addAnnotatedType( type, type.getJavaClass().getName() + "#" );
	}
}
