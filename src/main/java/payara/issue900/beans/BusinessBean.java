
package payara.issue900.beans;

public interface BusinessBean
{

	void regular_em1();

	void regular_em2();

	void beforeBeanDiscovery_em1();

	void beforeBeanDiscovery_em2();

	void afterTypeDiscovery_em1();

	void afterTypeDiscovery_em2();

	void afterBeanDiscovery_em1();

	void afterBeanDiscovery_em2();
}
