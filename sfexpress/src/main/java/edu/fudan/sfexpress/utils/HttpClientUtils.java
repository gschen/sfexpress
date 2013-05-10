package edu.fudan.sfexpress.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.fudan.sfexpress.constants.SystemConstants;

public class HttpClientUtils {

	public static HttpClient httpClient;

	public static HttpPost httpPost;

	public static HttpGet httpGet;

	public static HttpResponse httpResponse;

	static {
		if (null == httpClient) {
			final HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					SystemConstants.CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParams,
					SystemConstants.SO_TIMEOUT);
			HttpConnectionParams.setTcpNoDelay(httpParams, true);
			HttpConnectionParams.setStaleCheckingEnabled(httpParams, false);
			// httpParams.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS,
			// false); //禁止自动重定向

			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
					.getSocketFactory()));
			schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory
					.getSocketFactory()));
			PoolingClientConnectionManager cm = new PoolingClientConnectionManager(
					schemeRegistry);
			cm.setMaxTotal(2000);
			cm.setDefaultMaxPerRoute(200);
			httpClient = new DefaultHttpClient(cm, httpParams);
		}
	}

	public static void doPost(String url, List<NameValuePair> params) {

		httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			httpResponse = httpClient.execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void doGet(String url) {

		httpGet = new HttpGet(url);
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getResponseAsString() {

		String str = null;
		try {
			str = EntityUtils.toString(httpResponse.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	public static Document getResponseAsDocument() {

		Document doc = null;

		doc = Jsoup.parse(getResponseAsString());

		return doc;
	}
}
