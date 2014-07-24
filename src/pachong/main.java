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
//		xindan();
//		amazon();
//		dangdang();
//		suning2();
//		tmall();
//		yhd();
//		guomei();
		//test2
		yixun();
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
		System.out.println(name.asText()+"---" +price.asText()+"  link  "+link);
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
			System.out.println(name.asText()+"---" +price.asText()+"  link  "+link);
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
			final HtmlElement link = (HtmlElement) page.getByXPath("//div[@id='mainResults']//h3[@class='newaps']").get(i);
			final HtmlElement href=(HtmlElement) link.getElementsByTagName("a").get(0);
			String link2=href.getAttribute("href");
			System.out.println(name.asText()+"---" +price.asText()+"  link  "+link2);
			webClient.closeAllWindows();
		}
	}
	public static void dangdang() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://category.dangdang.com/cp01.05.16.00.00.00.html");
		for(int i=0;i<(page.getByXPath("//div[@class='book_shoplist']//div[@class='inner']//p[@class='name']").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@class='book_shoplist']//div[@class='inner']//p[@class='name']").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@class='book_shoplist']//div[@class='inner']/p[@class='price']/span[@class='price_n']").get(i);
			final HtmlElement href=(HtmlElement) name.getElementsByTagName("a").get(0);
			String link2=href.getAttribute("href");
			System.out.println(name.asText()+"---" +price.asText()+"  link  "+link2);
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
			HtmlPage page2 = (HtmlPage) webClient.getPage(link);
			final HtmlElement name = (HtmlElement) page2.getByXPath("//div[@class='product-info-box']//h2[@class='selling-points wb']").get(0);
			final HtmlElement price = (HtmlElement) page2.getByXPath("//div[@class='product-info-box']//span[@class='main-price snPrice fl pdr5']").get(0);
			System.out.println(name.asText()+"---" +price.asText());
			webClient.closeAllWindows();
		}
	}
	/*
	 * 这个方法就是用来单独测试具体商品页面的数据挖掘
	 * 还是失败
	 */
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
	
	public static void tmall() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://list.tmall.com/search_product.htm?spm=3.7396704.20000015.8.gVJ2G5&cat=50106434&sort=s&acm=tt-1143089-37249.1003.8.79822&from=sn_1_cat&pos=4&shopType=any&style=g&search_condition=55&industryCatId=50025829&active=1&uuid=79822&scm=1003.8.tt-1143089-37249.OTHER_1405292594731_79822#J_crumbs");
		for(int i=0;i<(page.getByXPath("//div[@id='J_ItemList']//div[@class='product-iWrap']//p[@class='productTitle']").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@id='J_ItemList']//div[@class='product-iWrap']//p[@class='productTitle']").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@id='J_ItemList']//div[@class='product-iWrap']//p[@class='productPrice']//em[@title]").get(i);
			final HtmlElement href=(HtmlElement) name.getElementsByTagName("a").get(0);
			String link=href.getAttribute("href");
			System.out.println(name.asText()+"---" +price.asText()+"  link  "+link);
			webClient.closeAllWindows();
		}
	}
	
	public static void yhd() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://www.yhd.com/ctg/s2/c21969-0/?tc=0.0.16.CatMenu_Site_100000003_6884_784.1699&tp=1.0.15.0.1084.TNr4As");
		for(int i=0;i<(page.getByXPath("//div[@id='plist']//p[@class='title']").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@id='plist']//p[@class='title']").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@id='plist']//div[@class='pricebox clearfix']/span[@yhdprice]").get(i);
			final HtmlElement href=(HtmlElement) name.getElementsByTagName("a").get(0);
			String link=href.getAttribute("href");
			System.out.println(name.asText()+"---" +price.asText()+"  link  "+link);
			webClient.closeAllWindows();
		}
		
		
	}
	
	
	public static void guomei() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://www.gome.com.cn/category/cat10000054.html");
		for(int i=0;i<(page.getByXPath("//div[@class='result-wrap ']//h2[@class='name']").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@class='result-wrap ']//h2[@class='name']").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@class='result-wrap ']//p[@class='price-wrap']/span[@class='price']").get(i);
			final HtmlElement href=(HtmlElement) name.getElementsByTagName("a").get(0);
			String link=href.getAttribute("href");
			System.out.println(name.asText()+"---" +price.asText()+"  link  "+link);
			webClient.closeAllWindows();
		}	
	}
	
	public static void yixun() throws Exception {
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		HtmlPage page = (HtmlPage) webClient.getPage("http://searchex.yixun.com/html?YTAG=2.1159614045055&&attr=55e7543&path=705882t705894&area=1");
		for(int i=0;i<(page.getByXPath("	//div[@class='goods']//div[@class='mod_goods_info']//p[@class='mod_goods_tit']").size());i++){
			final HtmlElement name = (HtmlElement) page.getByXPath("//div[@class='goods']//div[@class='mod_goods_info']//p[@class='mod_goods_tit']").get(i);
			final HtmlElement price = (HtmlElement) page.getByXPath("//div[@class='goods']//div[@class='mod_goods_info']/p[@class='mod_goods_price']//span[@class='mod_price']/span").get(i);
			final HtmlElement href=(HtmlElement) name.getElementsByTagName("a").get(0);
			String link=href.getAttribute("href");
			System.out.println(name.asText()+"---" +price.asText()+"  link  "+link);
			webClient.closeAllWindows();
		}	
	}
}

	

