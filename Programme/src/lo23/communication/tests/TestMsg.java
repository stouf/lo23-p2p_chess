package lo23.communication.tests;

import lo23.communication.message.Message;

public class TestMsg extends Message {
    
    private String content;
    
    public TestMsg(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
