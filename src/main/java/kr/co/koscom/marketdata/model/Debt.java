package kr.co.koscom.marketdata.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Debt implements Serializable{
	// TODO [실습 3-04] 종목 코드를 입력 받아 Fabot의 재무정보 API를 이용하여 해당 종목(기업)의 부채율 정보를 화면에 json형태로 출력한다.
	private int data_length;
	
	private ArrayList<Data> data;
	
	public int getData_length() {
		return data_length;
	}

	public void setData_length(int data_length) {
		this.data_length = data_length;
	}

	public ArrayList<Data> getData() {
		return data;
	}

	public void setData(ArrayList<Data> data) {
		this.data = data;
	}

	static class Data {
		private String quarter;
		private String stockcode;
		private double debt;
		private String stockname;
		private String desc;
		public String getQuarter() {
			return quarter;
		}
		public void setQuarter(String quarter) {
			this.quarter = quarter;
		}
		public String getStockcode() {
			return stockcode;
		}
		public void setStockcode(String stockcode) {
			this.stockcode = stockcode;
		}
		public double getDebt() {
			return debt;
		}
		public void setDebt(double debt) {
			this.debt = debt;
		}
		public String getStockname() {
			return stockname;
		}
		public void setStockname(String stockname) {
			this.stockname = stockname;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		
	}
}
