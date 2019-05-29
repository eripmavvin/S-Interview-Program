package process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxtLoader {

    public static List<Map<String, String>> readSourceFile(String pathname) {
        try {
        		FileReader reader = new FileReader(pathname);
        		BufferedReader br = new BufferedReader(reader); 
	            List<Map<String, String>> records = new ArrayList<>();
	            String line = null;
        		while ((line = br.readLine()) != null) {
                	Map<String, String> record = lineToMap(line);
                	records.add(record);
        		}
        		return records;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    public static void writeFile(List<Instrument> instruments, String writePath) {
        try {
            File writeName = new File(writePath);
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
            	for(Instrument instrument:instruments) {
            		out.write(instrument.dump()); 
            	}
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static List<MappingRule> readRuleFile(String path) {
		  try {
      		FileReader reader = new FileReader(path);
      		BufferedReader br = new BufferedReader(reader);
            List<MappingRule> rules = new ArrayList<>();
            String line = null;
      		while ((line = br.readLine()) != null) {
              	Map<String, String> record = lineToMap(line);
              	rules.add(new MappingRule(record));
      		}
      		return rules;
      } catch (IOException e) {
          e.printStackTrace();
      }
		return null;
	}
	
	public static Map<String, String> lineToMap(String line){
		int i = line.indexOf(":");
		int a = line.indexOf(";");
		if(i==-1|| a==-1) {
			return new HashMap<String,String>();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put(line.substring(0, i), line.substring(i+1,a));
		map.putAll(lineToMap(line.substring(a+1)));
		return map;
	}
}
