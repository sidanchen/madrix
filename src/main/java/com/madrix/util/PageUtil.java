package com.madrix.util;

import org.apache.log4j.Logger;

/**
 * 分类帮助工具类
 * 
 * @author sdc
 * @data 2017年10月18日
 */
public class PageUtil {

	private static Logger logger = Logger.getLogger(PageUtil.class);
	// 普通显示的数量
	public static final int SHOW_NUMBER = 4;
	
	//后台显示的数量
	public static final int ADMIN_SHOW_NUMBER = 10;
	/**
	 * 验证page的有效性
	 * 
	 * @param page
	 * @return
	 * @data 2017年7月26日
	 * @author sdc
	 */
	public static int checkPage(int page) {
		try {
			if (page <= 0) {
				return 1;
			}
			return page;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("验证page的有效性时出现错误:" + ex.toString());
			return 1;
		}
	}

	/**
	 * 计算每页开始的行数
	 * 
	 * @param page
	 * @return
	 * @data 2017年10月18日
	 * @author sdc
	 */
	public static int startRow(int page) {
		try {
			page = checkPage(page);
			return (page - 1) * SHOW_NUMBER;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("计算每页开始的行数发生错误:" + ex.toString());
			return 0;

		}
	}

	/**
	 * 计算每页开始的行数
	 * 
	 * @param page
	 * @return
	 * @data 2017年10月18日
	 * @author sdc
	 */
	public static int startRow(int page, int showNumber) {
		try {
			page = checkPage(page);
			return (page - 1) * showNumber;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("计算每页开始的行数时发生错误:" + ex.toString());
			return 0;
		}
	}

	/**
	 * 根据用户传过来的总行数计算出需要的页数
	 * 
	 * @param row
	 * @return
	 * @data 2017年7月26日
	 * @author sdc
	 */
	public static int countPage(int row) {
		try {
			if (row <= 0) {
				return 0;
			}
			return row % SHOW_NUMBER == 0 ? row / SHOW_NUMBER : row
					/ SHOW_NUMBER + 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("根据用户传过来的总行数计算出需要的页数出现异常:" + ex.toString());
			return 0;
		}
	}

	/**
	 * 根据用户传过来的总行数和显示的个数计算出需要的页数
	 * 
	 * @param page
	 * @return
	 * @data 2017年7月26日
	 * @author sdc
	 */
	public static int countPage(int row, int showNumber) {
		try {
			if (row <= 0) {
				return 0;
			}
			return row % showNumber == 0 ? row / showNumber : row / showNumber
					+ 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("根据用户传过来的总行数和显示的个数计算出需要的页数出现错误:" + ex.toString());
			return 0;
		}
	}

}
