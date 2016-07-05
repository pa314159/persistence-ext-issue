
package payara.issue900.beans;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class BusinessBeanImpl
implements BusinessBean
{

	@Inject
	private SomeBean1 bean1;

	@Inject
	private SomeBean2 bean2;

	@Override
	public void bean1_em1()
	{
		this.bean1.em1();
	}

	@Override
	public void bean1_em2()
	{
		this.bean1.em2();
	}

	@Override
	public void bean2_em1()
	{
		this.bean2.em1();
	}

	@Override
	public void bean2_em2()
	{
		this.bean2.em2();
	}
}
