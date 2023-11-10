package ecobridge.EcologyMap.dto;

import lombok.Data;

import java.util.List;

// ChatGPT API 응답의 구조를 나타내는 DTO 클래스 -> 응답의 ID, 모델, 메세지, 선택지 등의 정보를 포함
@Data
public class ChatResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    @Data
    public static class Choice {
        private int index;
        private Message message;
        private String finish_reason;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
    }

    @Data
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }


}
