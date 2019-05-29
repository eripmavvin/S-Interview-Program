package process;

public class Instrument {
	String instrumentId;
	String lastTradeDate;
	String deliveryDate;
	String market;
	String label;
	String tradable;
	
	public void setColumn(String column, String value, boolean overRide) {
		if(!overRide) {
			updateColumn(column,value);
			return;
		}
		switch (column) {
		case "INSTRUMENT_ID":
			instrumentId = value;
			break;
		case "LAST_TRADING_DATE":
			lastTradeDate = value;
			break;
		case "DELIVERY_DATE":
			deliveryDate = value;
			break;
		case "MARKET":
			market = value;		
			break;
		case "LABEL":
			label = value;
			break;
		case "TRADABLE":
			tradable = value;
			break;
		default:
			break;
		}
	}
	
	public String getColumn(String column) {
		switch (column) {
		case "INSTRUMENT_ID":
			return instrumentId;
		case "LAST_TRADING_DATE":
			return lastTradeDate;
		case "DELIVERY_DATE":
			return deliveryDate;
		case "MARKET":
			return market;
		case "LABEL":
			return label;
		case "TRADABLE":
			return tradable;
		default:
			return null;
		}
	}
	
	private void updateColumn(String column, String value) {
		switch (column) {
		case "INSTRUMENT_ID":
			if(instrumentId==null)
				instrumentId = value;
			break;
		case "LAST_TRADING_DATE":
			if(lastTradeDate==null)
				lastTradeDate = value;
			break;
		case "DELIVERY_DATE":
			if(deliveryDate==null)
				deliveryDate = value;
			break;
		case "MARKET":
			if(market==null)
				market = value;		
			break;
		case "LABEL":
			if(label==null)
				label = value;
			break;
		case "TRADABLE":
			if(tradable==null)
				tradable = value;
			break;
		default:
			break;
		}
	}
	
	public void updateWithInstance(Instrument updateData) {
		if(updateData.getDeliveryDate()!=null) {
			deliveryDate = updateData.getDeliveryDate();
		}
		if(updateData.getLastTradeDate()!=null) {
			lastTradeDate = updateData.getLastTradeDate();
		}
		if(updateData.getMarket()!=null) {
			market = updateData.getMarket();
		}
		if(updateData.getLabel()!=null) {
			label = updateData.getLabel();
		}
		if(updateData.getTradable()!=null) {
			tradable = updateData.getTradable();
		}
	}
	
	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getLastTradeDate() {
		return lastTradeDate;
	}

	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTradable() {
		return tradable;
	}

	public void setTradable(String tradable) {
		this.tradable = tradable;
	}



	public String dump() {
		String dump = "INSTRUMENT_ID:"+instrumentId+";"
				+ "LAST_TRADING_DATE:"+lastTradeDate+";"
				+ "DELIVERY_DATE:"+deliveryDate+";"
				+ "MARKET:"+market+";"
				+ "LABEL:"+label+";"
				+ "TRADABLE:"+tradable+";\r\n";
		return dump;
	}
}
