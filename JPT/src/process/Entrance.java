package process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entrance {

	public static void main(String[] args) {
		if(args.length!=3) {
			System.out.println("Args should be paths of rules, lme and prime txt file");
			return;
		}
		Entrance entrance = new Entrance();
		String prefix = entrance.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String prefixPath = prefix.substring(1, prefix.lastIndexOf("/")+1);
		String rulePath = prefixPath+args[0];
		String filePath1 = prefixPath+args[1];
		String filePath2 = prefixPath+args[2];
//		String rulePath = "D:\\JPT\\RULES.txt";
//		String filePath1 = "D:\\JPT\\LME.txt";
//		String filePath2 = "D:\\JPT\\PRIME.txt";
		String writePath = prefixPath+"result.txt";
		String source1 = "LME";
		String source2 = "PRIME";
		
		Map<String, List<MappingRule>> rules = MappingRule.getAllRules(rulePath);
		Map<String,List<Map<String, String>>> records = new HashMap<>();
		records.put(source1, TxtLoader.readSourceFile(filePath1));
		records.put(source2, TxtLoader.readSourceFile(filePath2));
		List<Instrument> instruments = MappingRule.mapping(rules, records);
		TxtLoader.writeFile(instruments,writePath);
	}
	
}
