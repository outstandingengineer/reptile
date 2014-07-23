package pachong;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;

public class main {
	public static void main(String args[]) throws Exception{
//		jingdong();
		xindan();
//		amazon();
//		dangdang();
//		suning2();

		}
	
	public static void jingdong() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://list.jd.com/list.html?cat=670,671,672");
		for(int i=0;i<(page.getByXPath("//div[@id='plist']//div[@class='p-name']").size());i++){
		final HtmlDivision name = (HtmlDivision) page.getByXPath("//div[@id='plist']//div[@class='p-name']").get(i);
		final HtmlDivision price = (HtmlDivision) page.getByXPath("//div[@id='plist']//div[@class='p-price']").get(i);
		final HtmlElement href=(HtmlElement) name.getElementsByTagName("a").get(0);
		String link=href.getAttribute("href");
		System.out.println(name.asText()+"---" +price.asText()+"i de "+i+" "+link);
		webClient.closeAllWindows();
		}
	}
	public static void xindan() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://www.newegg.cn/SubCategory/615.htm");
		for(int i=0;i<(page.getByXPath("//div[@class='catepro cateproA mt40']//p[@class='title']").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@class='catepro cateproA mt40']//p[@class='title']").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@class='catepro cateproA mt40']//p[@class='priceline']").get(i);
			final HtmlElement href=(HtmlElement) name.getElementsByTagName("a").get(0);
			String link=href.getAttribute("href");
			System.out.println(name.asText()+"---" +price.asText()+"link"+link);
			webClient.closeAllWindows();
		}
	}
	public static void amazon() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://www.amazon.cn/%E8%B6%85%E6%9E%81%E6%9C%AC/b/ref=sa_menu_office_l3_b148770071?ie=UTF8&node=148770071");
		for(int i=0;i<(page.getByXPath("//div[@id='mainResults']//h3[@class='newaps']//span[@class='lrg']").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@id='mainResults']//span[@class='lrg']").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@id='mainResults']//span[@class='bld lrg red']").get(i);
			System.out.println(name.asText()+"---" +price.asText()+"i de "+i+" ");
			webClient.closeAllWindows();
		}
	}
	public static void dangdang() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://category.dangdang.com/cp01.05.16.00.00.00.html");
		for(int i=0;i<(page.getByXPath("//div[@class='book_shoplist']//div[@class='inner']//p[@class='name']//a[@title]").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@class='book_shoplist']//div[@class='inner']//p[@class='name']//a[@title]").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@class='book_shoplist']//div[@class='inner']/p[@class='price']/span[@class='price_n']").get(i);
			System.out.println(name.asText()+"---" +price.asText());
			webClient.closeAllWindows();
		}
	}
	public static void suning() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://list.suning.com/0-243505-0.html");
		for(int i=0;i<(page.getByXPath("//div[@id='productTab']//img[@class='err-product']").size());i++){
			final HtmlElement getlink = (HtmlElement) page.getByXPath("//div[@id='productTab']//div[@class='inforBg']").get(i);
			final HtmlElement href=(HtmlElement) getlink.getElementsByTagName("a").get(0);
			String link=href.getAttribute("href");
			final WebClient webClient2=new WebClient(BrowserVersion.CHROME);
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setCssEnabled(false);
			HtmlPage page2 = (HtmlPage) webClient2.getPage(link);
			final HtmlElement name = (HtmlElement) page2.getByXPath("//div[@class='product-info-box']//h2[@class='selling-points wb']").get(0);
			final HtmlElement price = (HtmlElement) page2.getByXPath("//div[@class='product-info-box']//span[@class='main-price snPrice fl pdr5']").get(0);
			System.out.println(name.asText()+"---" +price.asText());
			webClient.closeAllWindows();
		}
	}
	public static void suning2() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://product.suning.com/104062513.html");
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@class='product-info-box']//h2[@class='selling-points wb']").get(0);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@class='product-info-box']//span[@class='main-price snPrice fl pdr5']").get(0);
			System.out.println(name.asText()+"---" +price.asText());
			webClient.closeAllWindows();
		}
	}
	

