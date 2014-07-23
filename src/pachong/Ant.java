package pachong;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Ant {
	ArrayList<String> title ;
	ArrayList<ArrayList<String>> value;
	WebClient webClient;
	
	public static void main(String args[]){
		Ant ant = new Ant();
		ant.readFromFile("test.txt");
		ant.printValue();
	}
	
	public void readFromFile(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		HtmlPage page = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (!(line.trim().equals("")||line.startsWith("##"))) {
					String[] tempArr = line.split("\t");
					List<String> list = new ArrayList<String>(Arrays.asList(tempArr));
					list.remove(0);
					if (line.startsWith(";;")) {
						title = new ArrayList<String>();
						value = new ArrayList<ArrayList<String>>();
						webClient = new WebClient(
								BrowserVersion.CHROME);
						webClient.getOptions().setJavaScriptEnabled(true);
						webClient.getOptions().setCssEnabled(false);
						// 增加不显示的警告的方法
						webClient.getOptions().setThrowExceptionOnScriptError(false);
						webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
						webClient.setAjaxController(new NicelyResynchronizingAjaxController());   
						webClient.waitForBackgroundJavaScript(600*1000);  
				        webClient.setJavaScriptTimeout(0); 
						try {
							page = webClient.getPage(list.get(0));
							list.remove(0);
							if((page = getIndex(page,list))==null){
								break;
							}
						} catch (FailingHttpStatusCodeException e) {
							System.out.println("无效网址");
							break;
						} catch (MalformedURLException e) {
							System.out.println("网络连接错误");
							break;
						} catch (IOException e) {
							System.out.println("IO错误");
							break;
						}
					} else {
						title.add(tempArr[0]);
						getList(page, list);
					}
				}

			}
		} catch (IOException e) {
			System.out.println("文件名错误");
		} finally {
			if(webClient!=null){
				webClient.closeAllWindows();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {

				}
			}
		}
	}
	
	public HtmlPage getIndex(HtmlPage page,List<String> xPathList){
		for(String xPath:xPathList){
			DomNode link = (DomNode) page.getByXPath (xPath).get(0);
			try {
				page = webClient.getPage(link.getTextContent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("主页中含无效路径");
				return null;
			}
		}
		return page;
	}
	
	public void getList(HtmlPage page, List<String> xPath) {
		if (xPath.size() > 1) {
			while (true) {
				if (xPath.size() != 1) {
					List links = (List) page.getByXPath(xPath.get(0));
					if (links != null) {
						if (links.size() == 1) {
							DomNode link = (DomNode) links.get(0);
							try {
								page = webClient.getPage(link.getTextContent());
								xPath.remove(0);
							} catch (IOException e) {
								System.out.println("配置文件中" + xPath.get(0)
										+ "链接失败!");
								break;
							}
						} else {
							xPath.remove(0);
							for (int j = 0; j < links.size(); j++) {
								DomNode link = (DomNode) links.get(j);
								try {
									HtmlPage p = webClient.getPage(link.getTextContent());
									getValue(p, xPath, j);
								} catch (IOException e) {
									System.out.println("配置文件中" + xPath.get(0)+ "引出的"+link+ "有误!");
									value.get(j).add(" ");
								}
							}
							return;
						}
					} else {
						System.out.println("配置文件中" + xPath.get(0) + "有误!");
						break;
					}
				} else{
					break;
				}
			}
		}
		for (int i = 0; i < (page.getByXPath(xPath.get(0)).size()); i++) {
			DomNode val = (DomNode) page.getByXPath(xPath.get(0))
					.get(i);
			if (title.size() == 1) {
				value.add(new ArrayList<String>());
			}
			value.get(i).add(val.getTextContent());
		}

	}
	
	public void getValue(HtmlPage page, List<String> xPath,int i) {
		for (int j = 0; j < xPath.size()-1; j++) {
			DomNode link = (DomNode) page.getByXPath (xPath.get(j)).get(0);
			try {
				page = webClient.getPage(link.getTextContent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("配置文件中链接不合法");
			}
		}
		DomNode ele = (DomNode) page.getByXPath(xPath.get(xPath.size()-1)).get(0);
		if (title.size() == 1) {
			value.add(new ArrayList<String>());
		}
		value.get(i).add(ele.getTextContent());
	}
	
	
	public void printValue(){
		for(String cell:title){
			System.out.print(cell+"\t");
		}
		System.out.println();
		for(ArrayList<String> list:value){
			for(String cell:list){
				System.out.print(cell+"\t");
			}
			System.out.println();
		}
	}
}
