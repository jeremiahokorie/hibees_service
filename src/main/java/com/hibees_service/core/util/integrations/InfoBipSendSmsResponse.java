package com.hibees_service.core.util.integrations;import com.fasterxml.jackson.annotation.JsonIgnoreProperties;import lombok.AllArgsConstructor;import lombok.Builder;import lombok.Data;import lombok.NoArgsConstructor;import java.util.List;/** * @author Shalom Olomolaiye * @philosophy It's not "CODE" It's how you think... Think and solve the problem * Growth is when you keep finding efficient ways to solve the problem * <p> * ------ * Tip: Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live. * 'Cause he is violent,and he knows where you live. * ------ * Anyway, welcome to the InfoBipSendSmsResponse class... HAPPY THINKING * @since 15/04/2022 6:11 PM */@Data@Builder@AllArgsConstructor@NoArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)public class InfoBipSendSmsResponse {    private String bulkId;    private List<InfoBipMessagesResponse> messages;}