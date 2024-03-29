import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static String h="黑龙江";
	static String sq="呼和浩特乌鲁木齐五大连池香格里拉锡林浩特齐齐哈尔克拉玛依额尔古纳霍林郭勒呼伦贝尔鄂尔多斯乌兰浩特乌兰察布巴彦淖尔二连浩特加格达奇";
    static	String sp="马鞍山六盘水秦皇岛石家庄驻马店三门峡哈尔滨牡丹江佳木斯梅河口连云港张家港井冈山景德镇海拉尔石嘴山青铜峡德令哈格尔木攀枝花日喀则吐鲁番石河子库尔勒阿克苏";

	public static List<Map<String, String>> addressResolution(String str,String flag) {

		
//		String province = null, city = null, county = null, town = null, road = null, numb = null, village = null;
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		Map<String, String> row = null;
		row = new LinkedHashMap<String, String>();
		//获取省份
		String province="";
		if(str.indexOf("省")!=-1)
		{
			province=str.substring(0, str.indexOf("省")+1);
			str=str.substring(str.indexOf("省")+1);
		}
		else if(str.indexOf(h)!=-1) {
			province=h+"省";
			str=str.replaceFirst(h, "");
		}
		else if(str.indexOf("自治区")!=-1)
		{
			province=str.substring(0, str.indexOf("自治区")+3);
			str=str.substring(str.indexOf("自治区")+3);
		}
		else if(str.indexOf("行政区")!=-1)
		{
			province=str.substring(0, str.indexOf("行政区")+3);
			str=str.substring(str.indexOf("行政区")+3);
		}	
		else if(str.indexOf("北京")!=-1)
			province="北京";
		else if(str.indexOf("天津")!=-1)
			province="天津";
		else if(str.indexOf("上海")!=-1)
			province="上海";
		else if(str.indexOf("重庆")!=-1)
			province="重庆";
		else
		{
			province=str.substring(0,2)+"省";
			str=str.substring(2);
		}
		//提取市
		String city="";
		if(str.indexOf("市")!=-1)
		{
			city=str.substring(0, str.indexOf("市")+1);
			str=str.substring(str.indexOf("市")+1);
		}else if(str.indexOf("岛")!=-1){
			city=str.substring(0, str.indexOf("岛")+1);
			str=str.substring(str.indexOf("岛")+1);
		}else if(str.indexOf("自治州")!=-1){
			city=str.substring(0, str.indexOf("自治州")+3);
			str=str.substring(str.indexOf("自治州")+3);
		}else if(str.indexOf("地区")!=-1){
			city=str.substring(0, str.indexOf("地区")+2);
			str=str.substring(str.indexOf("地区")+2);
		}else if(str.indexOf("盟")!=-1){
			city=str.substring(0, str.indexOf("盟")+1);
			str=str.substring(str.indexOf("盟")+1);
		}else{
			int x=0;
			for(int i=0;i<sp.length();i=i+3)
				{
					String c = sp.substring(i, i + 3);
					if(str.indexOf(c)!=-1) {
						city=c+"市";
						str=str.replaceFirst(c, "");
						x=1;
						break;
					}
				}
			if(x==0) {
				for(int i=0;i<sq.length();i=i+4)
				{
					String c = sq.substring(i, i + 4);
					if(str.indexOf(c)!=-1) {
						city=c+"市";
						str=str.replaceFirst(c, "");
						x=1;
						break;
					}
				}
			}
			if(x==0) {
				city=str.substring(0, 2)+"市";
				str=str.replaceFirst(str.substring(0, 2), "");
			}
		}

		
		//提取区县
		String county="";
		if(str.indexOf("区")!=-1)
		{
			county=str.substring(0, str.indexOf("区")+1);
			str=str.substring(str.indexOf("区")+1);
		}else if(str.indexOf("县")!=-1)
		{
			county=str.substring(0, str.indexOf("县")+1);
			str=str.substring(str.indexOf("县")+1);
		}

		
		//提取街道/镇
		String town="";
		if(str.indexOf("街道")!=-1)
		{
			town=str.substring(0, str.indexOf("街道")+2);
			str=str.substring(str.indexOf("街道")+2);
		}else if(str.indexOf("镇")!=-1)
		{
			town=str.substring(0, str.indexOf("镇")+1);
			str=str.substring(str.indexOf("镇")+1);
		}else if(str.indexOf("乡")!=-1)
		{
			town=str.substring(0, str.indexOf("乡")+1);
			str=str.substring(str.indexOf("乡")+1);
		}
		if(flag.equals("1")) {
		row.put("province", province == null ? "" : province.trim());		
		row.put("city", city == null ? "" : city.trim());		
		row.put("county", county == null ? "" : county.trim());	
		row.put("town", town == null ? "" : town.trim());
		row.put("road", str == null ? "" : str.trim());
	table.add(row);
	return table;
		}
		else {
			//提取路
			String way="";
			if(str.indexOf("路")!=-1){
				way=str.substring(0, str.indexOf("路")+1);
				str=str.substring(str.indexOf("路")+1);
			}else if(str.indexOf("街")!=-1){
				way=str.substring(0, str.indexOf("街")+1);
				str=str.substring(str.indexOf("街")+1);
			}else if(str.indexOf("巷")!=-1){
				way=str.substring(0, str.indexOf("巷")+1);
				str=str.substring(str.indexOf("巷")+1);
			}else if(str.indexOf("村")!=-1){
				way=str.substring(0, str.indexOf("村")+1);
				str=str.substring(str.indexOf("村")+1);
			}
			
			
			//提取门牌号
			String tablet="";
			if(str.indexOf("号")!=-1)
			{
				tablet=str.substring(0, str.indexOf("号")+1);
				str=str.substring(str.indexOf("号")+1);
			}

			row.put("province", province == null ? "" : province.trim());

			row.put("city", city == null ? "" : city.trim());

			row.put("county", county == null ? "" : county.trim());

			row.put("town", town == null ? "" : town.trim());

			row.put("road", way == null ? "" : way.trim());

			row.put("numb", tablet == null ? "" : tablet.trim());

			row.put("village", str == null ? "" : str.trim());
		table.add(row);
		return table;


		}	

	}
	public static String phone_number(String num) {
		if (num == null || num.length() == 0) {
			return "";
		}
		Pattern pattern = Pattern.compile("\\d{11}");
		Matcher matcher = pattern.matcher(num);
		StringBuffer bf = new StringBuffer(64);
		while (matcher.find()) {
			bf.append(matcher.group()).append(",");
		}
		int len = bf.length();
		if (len > 0) {
			bf.deleteCharAt(len - 1);
		}
		return bf.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {

//		File inputFile = new File("in.txt");
		File inputFile = new File(args[0]);
		Scanner in = new Scanner(inputFile);
//		PrintWriter out = new PrintWriter("2.txt");
		PrintWriter out = new PrintWriter(args[1]);
		// Scanner in = new Scanner(System.in);
		String [] Address = new String [5000];
		for(int j=0;in.hasNext();j++)
		{
			//Address [i] = new String();
			Address [j] = in.nextLine();
		}
		int len=0;
		for (int i=0;Address[i]!=null;i++)
		{
			len++;
		}
		out.print("[\r\n");
		
		for(int i=0;i<len;i++) {
			String address = Address[i];
			String diff = address.substring(0, address.indexOf("!"));
			String address_1 = address.substring(address.indexOf("!") + 1);
			String name = address_1.substring(0, address_1.indexOf(","));
			String address_2 = address_1.substring(address_1.indexOf(",") + 1);
			String mobile = phone_number(address_2);
			String address_3 = address_2.replace(mobile, "");
			String address_4 = address_3.substring(0, address_3.indexOf("."));
			// System.out.println(address_2);
			out.print("{\"姓名\":" + "\"" + name + "\",");
			out.print("\"手机\":" + "\"" + mobile + "\",");
			out.print("\"地址\":[");
			List<Map<String, String>> table = addressResolution(address_4,diff);
			out.print("\"" + table.get(0).get("province") + "\",");
			out.print("\"" + table.get(0).get("city") + "\",");
			out.print("\"" + table.get(0).get("county") + "\",");
			out.print("\"" + table.get(0).get("town") + "\",");
			if(diff.equals("1"))
			{
				
				out.print("\"" + table.get(0).get("road") +"\"");
			}
			
			else
			{
				//List<Map<String, String>> table = addressResolution(address_4);
				
				out.print("\"" + table.get(0).get("road") + "\",");
				out.print("\"" + table.get(0).get("numb") + "\",");
				out.print("\"" + table.get(0).get("village") + "\"");
			}
			// System.out.println(table);
			if(i!=len-1)
			out.print("]},\r\n");
			else
			out.print("]}\r\n");

		}
		out.print("]");
		in.close();
		out.close();
		}
}