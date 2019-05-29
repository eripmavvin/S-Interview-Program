package process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MappingRule {

	private String source;
	private String sourceColumn;
	private String targetColumn;
	private String operator;
	private String value;

	public static final String OP_TRUST = "TRUST";
	public static final String OP_REMOVE = "REMOVE";
	public static final String OP_REPLACE = "REPLACE";
	public static final String OP_NOT_USE = "NOT_USE";
	public static final String OP_UP_TO_DATE = "UP_TO_DATE";
	public static final String OP_DEFAULT = "DEFAULT";
	
	public MappingRule(Map<String, String> record) {
		for(Entry<String, String> entry: record.entrySet()) {
			switch (entry.getKey()) {
			case "SOURCE":
				source = entry.getValue();
				break;
			case "SOURCE_COLUMN":
				sourceColumn = entry.getValue();
				break;
			case "TARGET_COLUMN":
				targetColumn = entry.getValue();
				break;
			case "OPERATOR":
				operator = entry.getValue();
				break;
			case "VALUE":
				value = entry.getValue();
				break;
	
			default:
				break;
			}
		}
	}

	public static List<Instrument> mapping(Map<String, List<MappingRule>> rules, Map<String,List<Map<String, String>>> records) {
		Map<String, Instrument> instrumentMap = new HashMap<>();
		
		for(Entry<String, List<Map<String, String>>> sourceRecords: records.entrySet()) {
			List<MappingRule> sourceRules = rules.get(sourceRecords.getKey());
			if(sourceRules != null) {
				for(Map<String, String> record:sourceRecords.getValue()) {
					Instrument instrument = new Instrument();
					for(MappingRule rule: sourceRules) {
						switch (rule.getOperator()) {
							case OP_TRUST:
								instrument.setColumn(rule.getTargetColumn(), record.get(rule.getSourceColumn()),true);
								break;
							case OP_REMOVE:
								instrument.setColumn(rule.getTargetColumn(), record.get(rule.getSourceColumn()).replace(rule.value, ""),true);
							default:
								break;
						}
					}
					if(instrumentMap.get(instrument.getInstrumentId())==null) {
						for(MappingRule rule: sourceRules) {
							switch (rule.getOperator()) {
							case OP_UP_TO_DATE:
								instrument.setColumn(rule.getTargetColumn(), record.get(rule.getSourceColumn()),false);
								break;
							case OP_DEFAULT:
								instrument.setColumn(rule.getTargetColumn(), rule.getValue(),false);
							default:
								break;
							}
						}
						instrumentMap.put(instrument.getInstrumentId(), instrument);
					}else {
						Instrument originInstrument = instrumentMap.get(instrument.getInstrumentId());
						for(MappingRule rule: sourceRules) {
							switch (rule.getOperator()) {
							case OP_UP_TO_DATE:
								if(originInstrument.getColumn(rule.getTargetColumn())==null)
									instrument.setColumn(rule.getTargetColumn(), record.get(rule.getSourceColumn()),false);
								break;
							case OP_DEFAULT:
								if(originInstrument.getColumn(rule.getTargetColumn())==null)
									instrument.setColumn(rule.getTargetColumn(), rule.getValue(),false);
							default:
								break;
							}
						}
						originInstrument.updateWithInstance(instrument);
					}
				}
			}
		}
		return new ArrayList<Instrument>(instrumentMap.values());
	}
	
	public static Map<String, List<MappingRule>> getAllRules(String path){
		List<MappingRule> rules = TxtLoader.readRuleFile(path);
		Map<String, List<MappingRule>> sourceRuleMap = new HashMap<>();
		for(MappingRule rule:rules) {
			if(sourceRuleMap.get(rule.getSource())==null) {
				List<MappingRule> sourceRules = new ArrayList<>();
				sourceRules.add(rule);
				sourceRuleMap.put(rule.getSource(), sourceRules);
			}else {
				sourceRuleMap.get(rule.getSource()).add(rule);
			}
		}
		return sourceRuleMap;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceColumn() {
		return sourceColumn;
	}

	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}

	public String getTargetColumn() {
		return targetColumn;
	}

	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

