package com.cisco.cre.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NeighborItem extends GenericItem {

		private double score;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yyyy", timezone="PST")
		private Date itemEOLDate;
		
		public NeighborItem() {
			super();
			// TODO Auto-generated constructor stub
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public Date getItemEOLDate() {
			return itemEOLDate;
		}
		public void setItemEOLDate(Date itemEOLDate) {
			this.itemEOLDate = itemEOLDate;
		}
		
		@Override
		public String toString() {
			return "NeighborItem [score=" + score + ", itemEOLDate="
					+ itemEOLDate + ", name=" + name + ", description="
					+ description + ", itemType=" + itemType + ", productType="
					+ productType + ", productFamily=" + productFamily + "]";
		}
		
		
}
