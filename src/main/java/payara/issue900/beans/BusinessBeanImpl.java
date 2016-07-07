
package payara.issue900.beans;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class BusinessBeanImpl
implements BusinessBean
{

	@Inject
	private RegularBean regularBean;

	@Inject
	private BeforeBeanDiscoveryBean bbdBean;

	@Inject
	private AfterTypeDiscoveryBean atdBean;

	@Inject
	private AfterBeanDiscoveryBean abdBean;

	@Override
	public void regular_em1()
	{
		this.regularBean.em1();
	}

	@Override
	public void regular_em2()
	{
		this.regularBean.em2();
	}

	@Override
	public void beforeBeanDiscovery_em1()
	{
		this.atdBean.em1();
	}

	@Override
	public void beforeBeanDiscovery_em2()
	{
		this.atdBean.em2();
	}

	@Override
	public void afterTypeDiscovery_em1()
	{
		this.bbdBean.em1();
	}

	@Override
	public void afterTypeDiscovery_em2()
	{
		this.bbdBean.em2();
	}

	@Override
	public void afterBeanDiscovery_em1()
	{
		this.abdBean.em1();
	}

	@Override
	public void afterBeanDiscovery_em2()
	{
		this.abdBean.em2();
	}
}
