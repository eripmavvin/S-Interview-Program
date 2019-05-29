package process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class test {

	public static void main(String[] args) {
		
//		Map<String, String> map = TxtLoader.lineToMap("INSTRUMENT_ID:PRIME_PB_03_2018;LAST_TRADING_DATE:14-03-2018;DELIVERY_DATE:18-03-2018;MARKET:LME_PB;LABEL:Lead 13 March 2018;EXCHANGE_CODE:PB_03_2018;TRADABLE:FALSE");
//		for(Entry<String, String> entry: map.entrySet()) {
//			System.out.println(entry.getKey()+":"+entry.getValue());
//		}
		
//		List<Map<String, String>> records = TxtLoader.readSourceFile("D:\\JPT\\LME.txt");
//		for(Map<String, String>record:records) {
//			System.out.println("-----------------------------");
//			for(Entry<String, String> entry: record.entrySet()) {
//				System.out.println(entry.getKey()+":"+entry.getValue());
//			}
//		}
		Entrance entrance = new Entrance();
		String prefix = entrance.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(prefix);
		String prefixPath = prefix.substring(1, prefix.lastIndexOf("/")+1);
		System.out.println(prefixPath);
	}
}
