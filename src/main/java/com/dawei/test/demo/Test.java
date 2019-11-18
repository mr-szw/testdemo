//package com.dawei.test.demo;
//
///**
// * Create by Dawei on 2019/10/31
// */
//public class Test {
//
//	public static void main(String[] args) {
//		// The payload definition using the Jackson library
//		JsonNodeFactory jnf = JsonNodeFactory.instance;
//		ObjectNode payload = jnf.objectNode();
//		{
//			ObjectNode update = payload.putObject("update");
//			{
//				ArrayNode worklog = update.putArray("worklog");
//				ObjectNode worklog0 = worklog.addObject();
//				{
//					ObjectNode add = worklog0.putObject("add");
//					{
//						add.put("timeSpent", "60m");
//						add.put("started", "2019-07-05T11:05:00.000+0000");
//					}
//				}
//			}
//			ObjectNode fields = payload.putObject("fields");
//			{
//				fields.put("summary", "Main order flow broken");
//				ObjectNode parent = fields.putObject("parent");
//				{
//					parent.put("key", "PROJ-123");
//				}
//				ObjectNode issuetype = fields.putObject("issuetype");
//				{
//					issuetype.put("id", "10000");
//				}
//				ArrayNode components = fields.putArray("components");
//				ObjectNode components0 = components.addObject();
//				{
//					components0.put("id", "10000");
//				}
//				fields.put("customfield_20000", "06/Jul/19 3:25 PM");
//				fields.put("customfield_40000", "Occurs on all orders");
//				ArrayNode customfield_70000 = fields.putArray("customfield_70000");
//				customfield_70000.add("jira-administrators");
//				customfield_70000.add("jira-software-users");
//				ObjectNode project = fields.putObject("project");
//				{
//					project.put("id", "10000");
//				}
//				fields.put("description", "Order entry fails when selecting supplier.");
//				ObjectNode reporter = fields.putObject("reporter");
//				{
//					reporter.put("id", "5b10a2844c20165700ede21g");
//				}
//				ArrayNode fixVersions = fields.putArray("fixVersions");
//				ObjectNode fixVersions0 = fixVersions.addObject();
//				{
//					fixVersions0.put("id", "10001");
//				}
//				fields.put("customfield_10000", "09/Jun/19");
//				ObjectNode priority = fields.putObject("priority");
//				{
//					priority.put("id", "20000");
//				}
//				ArrayNode labels = fields.putArray("labels");
//				labels.add("bugfix");
//				labels.add("blitz_test");
//				ObjectNode timetracking = fields.putObject("timetracking");
//				{
//					timetracking.put("remainingEstimate", "5");
//					timetracking.put("originalEstimate", "10");
//				}
//				ArrayNode customfield_30000 = fields.putArray("customfield_30000");
//				customfield_30000.add("10000");
//				customfield_30000.add("10002");
//				ObjectNode customfield_80000 = fields.putObject("customfield_80000");
//				{
//					customfield_80000.put("value", "red");
//				}
//				ObjectNode security = fields.putObject("security");
//				{
//					security.put("id", "10000");
//				}
//				fields.put("environment", "UAT");
//				ArrayNode versions = fields.putArray("versions");
//				ObjectNode versions0 = versions.addObject();
//				{
//					versions0.put("id", "10000");
//				}
//				fields.put("duedate", "2019-03-11T00:00:00.000Z");
//				fields.put("customfield_60000", "jira-software-users");
//				fields.put("customfield_50000", "Could impact day-to-day work.");
//				ObjectNode assignee = fields.putObject("assignee");
//				{
//					assignee.put("id", "5b109f2e9729b51b54dc274d");
//				}
//			}
//		}
//
//		// Connect Jackson ObjectMapper to Unirest
//		Unirest.setObjectMapper(new ObjectMapper() {
//			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
//
//			public <T> T readValue(String value, Class<T> valueType) {
//				try {
//					return jacksonObjectMapper.readValue(value, valueType);
//				} catch (IOException e) {
//					throw new RuntimeException(e);
//				}
//			}
//
//			public String writeValue(Object value) {
//				try {
//					return jacksonObjectMapper.writeValueAsString(value);
//				} catch (JsonProcessingException e) {
//					throw new RuntimeException(e);
//				}
//			}
//		});
//
//		// This code sample uses the 'Unirest' library:
//		// http://unirest.io/java.html
//		HttpResponse<JsonNode> response = Unirest.post("/rest/api/2/issue")
//				.basicAuth("email@example.com", "<api_token>").header("Accept", "application/json")
//				.header("Content-Type", "application/json").body(payload).asJson();
//
//		System.out.println(response.getBody());
//	}
//
//}
