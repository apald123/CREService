package com.cisco.cre.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NeighborItem extends Item {

		private double score;
		
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
		
		@Override
		public String toString() {
			return "NeighborItem [score=" + score + ", partNumber="
					+ partNumber + ", description=" + description
					+ ", itemType=" + itemType + ", productType=" + productType
					+ ", productFamily=" + productFamily + ", serviceSKU="
					+ serviceSKU + ", eolAnnounceDate=" + eolAnnounceDate
					+ ", bu=" + bu + ", eolSaleDate=" + eolSaleDate
					+ ", lastSupportDate=" + lastSupportDate + ", leadTime="
					+ leadTime + ", techGroup=" + techGroup
					+ ", productSubGroup=" + productSubGroup + "]";
		}
		
		
}
