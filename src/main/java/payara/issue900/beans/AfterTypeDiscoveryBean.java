
package payara.issue900.beans;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.lang.String.format;
import static org.junit.Assert.assertNotNull;

public class AfterTypeDiscoveryBean
{

	private static final Logger L = Logger.getLogger( AfterTypeDiscoveryBean.class.getSimpleName() );

	@PersistenceContext( unitName = "PU1" )
	private EntityManager em1;

	private EntityManager em2;

	@PostConstruct
	private void postConstruct()
	{
		L.info( format( "Constructed, em1 = %s", this.em1 ) );
		L.info( format( "Constructed, em2 = %s", this.em2 ) );
	}

	@PersistenceContext( unitName = "PU2" )
	public void setEM2( EntityManager em )
	{
		this.em2 = em;

		L.info( format( "SET em2 as %s", this.em2 ) );
	}

	public void em1()
	{
		assertNotNull( "EM1 is NULL", this.em1 );
	}

	public void em2()
	{
		assertNotNull( "EM2 is NULL", this.em2 );
	}
}
