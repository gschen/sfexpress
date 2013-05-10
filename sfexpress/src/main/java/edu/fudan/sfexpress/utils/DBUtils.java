package edu.fudan.sfexpress.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import edu.fudan.sfexpress.constants.SfexpressTable;
import edu.fudan.sfexpress.pojo.ServiceTime;

public class DBUtils {
	private static final Logger log = Logger.getLogger(DBUtils.class);
	private static Connection connection;
	private final static String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static String dbName;
	private static String dbUrlString = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";

	public static void createDb(String dbName) {
		try {
			copyFile(new File("demo.accdb"), new File(dbName));
			DBUtils.dbName = dbName;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createTable() {

		StringBuffer provinceSql = new StringBuffer("CREATE TABLE "
				+ SfexpressTable.PROVINCE_TABLE + "(");

		provinceSql.append("id counter primary key, ");
		provinceSql.append(SfexpressTable.PROVINCE_TABLE_COLUMN_1
				+ " varchar(50))");

		log.info(provinceSql.toString());
		createTable(provinceSql.toString());

		StringBuffer citySql = new StringBuffer("CREATE TABLE "
				+ SfexpressTable.CITY_TABLE + "(");

		citySql.append("id counter primary key, ");
		citySql.append(SfexpressTable.CITY_TABLE_COLUMN_1 + " varchar(50))");

		log.info(citySql.toString());
		createTable(citySql.toString());

		StringBuffer areaSql = new StringBuffer("CREATE TABLE "
				+ SfexpressTable.AREA_TABLE + "(");

		areaSql.append("id counter primary key, ");
		areaSql.append(SfexpressTable.AREA_TABLE_COLUMN_1 + " varchar(50))");

		log.info(areaSql.toString());
		createTable(areaSql.toString());

		StringBuffer countrySql = new StringBuffer("CREATE TABLE "
				+ SfexpressTable.COUNTRY_TABLE + "(");

		countrySql.append("id counter primary key, ");
		countrySql.append(SfexpressTable.COUNTRY_TABLE_COLUMN_1
				+ " varchar(50))");

		log.info(countrySql.toString());
		createTable(countrySql.toString());

		StringBuffer serviceTimeSql = new StringBuffer("CREATE TABLE "
				+ SfexpressTable.SERVICE_TIME_TABLE + "(");

		serviceTimeSql.append("id counter primary key, ");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_1
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_2
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_3
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_4
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_5
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_6
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_7
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_8
				+ " integer,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_9
				+ " double,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_10
				+ " double,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_11
				+ " double,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_12
				+ " double,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_13
				+ " double,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_14
				+ " double,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_15
				+ " double,");
		serviceTimeSql.append(SfexpressTable.SERVICE_TIME_TABLE_COLUMN_16
				+ " varchar(50))");

		log.info(serviceTimeSql.toString());
		createTable(serviceTimeSql.toString());
	}

	public static void createTable(String sqlStr) {
		if (null == connection) {
			connect();
		}
		try {
			Statement stat = connection.createStatement();
			stat.execute(sqlStr);
			stat.close();
		} catch (Exception e) {
			log.error(e.getStackTrace());
			e.printStackTrace();
		}
	}

	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static void connect() {
		try {
			Class.forName(driver);
			Properties prop = new Properties();
			prop.put("charSet", "GBK"); // 解决中文乱码
			connection = DriverManager
					.getConnection(dbUrlString + dbName, prop);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log.info("Connect db success!");
		}
	}

	public static void saveProvince(String province) {
		save(SfexpressTable.PROVINCE_TABLE + "("
				+ SfexpressTable.PROVINCE_TABLE_COLUMN_1 + ") values('"
				+ province + "')");
		log.info("save province(" + province + ") success!");
	}

	public static void saveCity(String city) {
		save(SfexpressTable.CITY_TABLE + "("
				+ SfexpressTable.CITY_TABLE_COLUMN_1 + ") values('" + city
				+ "')");
		log.info("save city(" + city + ") success!");
	}

	public static void saveArea(String area) {
		save(SfexpressTable.AREA_TABLE + "("
				+ SfexpressTable.AREA_TABLE_COLUMN_1 + ") values('" + area
				+ "')");
		log.info("save area(" + area + ") success!");
	}

	public static void saveCountry(String country) {
		save(SfexpressTable.COUNTRY_TABLE + "("
				+ SfexpressTable.COUNTRY_TABLE_COLUMN_1 + ") values('"
				+ country + "')");
		log.info("save country(" + country + ") success!");
	}

	public static void saveServiceTime(ServiceTime st) {
		save(SfexpressTable.SERVICE_TIME_TABLE + "("
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_1 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_2 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_3 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_4 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_5 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_6 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_7 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_8 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_9 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_10 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_11 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_12 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_13 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_14 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_15 + ", "
				+ SfexpressTable.SERVICE_TIME_TABLE_COLUMN_16 + ") values("

				+ st.getSrcAddr().getProvinceNo() + ","
				+ st.getSrcAddr().getCityNo() + ","
				+ st.getSrcAddr().getAreaNo() + ","
				+ st.getSrcAddr().getCountryNo() + ","
				+ st.getDstAddr().getProvinceNo() + ","
				+ st.getDstAddr().getCityNo() + ","
				+ st.getDstAddr().getAreaNo() + ","
				+ st.getDstAddr().getCountryNo() + "," + st.getT1_time() + ","
				+ st.getT4_time() + "," + st.getSp4_time() + ","
				+ st.getT801_time() + "," + st.getSp5_time() + ","
				+ st.getT6_time() + "," + st.getAddTimeInfo().getAddTime()
				+ ",'" + st.getAddTimeInfo().getAddAddr() + "')");
		log.info("save service_time(" + st.toString() + ") success!");
	}

	public static void save(String sqlAppend) {
		try {
			Statement stat = connection.createStatement();
			String sql = "insert into " + sqlAppend;
			// System.out.println("sql is: " + sql);
			int result = stat.executeUpdate(sql);

			if (1 == result) {
				stat.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean close() {
		if (null != connection) {
			try {
				connection.close();
				connection = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				log.info("close db success!");
			}
		}
		return false;
	}
}
