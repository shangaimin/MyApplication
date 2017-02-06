package liangpin.myapplication.ndk;

/**
 * Created by Admin on 2017/2/5.
 */

public class TestModel {
    private String name;
    private int age;
    private String url;
    private TestModel(Builder builder){
        this.age=builder.age;
        this.name=builder.name;
        this.url=builder.url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Builder{
        private String name;
        private int age;
        private String url;
        public TestModel builder(){
            return new TestModel(this);
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder age(int age){
            this.age=age;
            return this;
        }
        public Builder url(String url){
            this.url=url;
            return this;
        }

    }
}
