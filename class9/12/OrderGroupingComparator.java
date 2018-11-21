package com.css.order.mr;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupingComparator extends WritableComparator{

	// 构造必须加
	protected OrderGroupingComparator() {
		super(OrderBean.class, true);
	}

	// 重写比较
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		OrderBean aBean = (OrderBean) a;
		OrderBean bBean = (OrderBean) b;
		int rs;
		// id不同不是同一对象
		if (aBean.getOrder_id() > bBean.getOrder_id()) {
			rs = 1;
		}else if (aBean.getOrder_id() < bBean.getOrder_id()) {
			rs = -1;
		}else {
			rs = 0;
		}
		return rs;
	}
}
