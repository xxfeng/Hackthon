package com.hackthon.domain;


// another table boardOrderTable(table_id, board_id, order_id, reversed_time)
public class Board {
	private String board_id;
	private String numPeople;
	private String status;
	private String no;
	
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getNumPeople() {
		return numPeople;
	}
	public void setNumPeople(String numPeople) {
		this.numPeople = numPeople;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
}
