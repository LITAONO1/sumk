/**
 * Copyright (C) 2016 - 2017 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yx.http.filter;

public class LoginObject {

	private String errorMsg;

	private String json;

	private String userId;

	public String getJson() {
		return json;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getUserId() {
		return userId;
	}

	public static LoginObject error(String errorMsg) {
		LoginObject obj = new LoginObject();
		obj.errorMsg = errorMsg;
		return obj;
	}

	public static LoginObject success(String text) {
		return success(text, null);
	}

	public static LoginObject success(String text, String userId) {
		LoginObject obj = new LoginObject();
		obj.json = text;
		obj.userId = userId;
		return obj;
	}
}
