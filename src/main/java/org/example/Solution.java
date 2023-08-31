package org.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class Result {

    /*
     * Complete the 'topArticles' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER limit as parameter.
     * base url for copy/paste:
     * https://jsonmock.hackerrank.com/api/articles?page=<pageNumber>
     */

    public static Response getMockResponse() {
        return new Response(
                1,
                10,
                41,
                5,
                List.of(
                        new Data(
                                "A Message to Our Customers",
                                "http://www.apple.com/customer-letter/",
                                "epaga",
                                967L,
                                null,
                                null,
                                null,
                                null,
                                1455698317L
                        ),
                        new Data("Was isolated from 1999 to 2006 with a 486. Built my own late 80s OS",
                                "http://imgur.com/gallery/hRf2trV",
                                "epaga",
                                265L,
                                null,
                                null,
                                null,
                                null,
                                1418517626L
                        ),
                        new Data("Apple’s declining software quality",
                                "http://sudophilosophical.com/2016/02/04/apples-declining-software-quality/",
                                "epaga",
                                705L,
                                null,
                                null,
                                null,
                                null,
                                1454596037L
                        ),
                        new Data(
                                null,
                                null,
                                "patricktomas",
                                376L,
                                null,
                                "Steve Jobs has passed away.",
                                "http://www.apple.com/stevejobs/",
                                null,
                                1317858143L
                        ),
                        new Data(
                                "Google Is Eating Our Mail",
                                "https://www.tablix.org/~avian/blog/archives/2019/04/google_is_eating_our_mail/",
                                "saintamh",
                                685L,
                                null,
                                null,
                                null,
                                null,
                                1556274921L
                        ),
                        new Data(
                                "Why I’m Suing the US Government",
                                "https://www.bunniestudios.com/blog/?p=4782",
                                "saintamh",
                                305L,
                                null,
                                null,
                                null,
                                null,
                                1469106658L
                        ),
                        new Data(
                                "F.C.C. Repeals Net Neutrality Rules",
                                "https://www.nytimes.com/2017/12/14/technology/net-neutrality-repeal-vote.html",
                                "panny",
                                1397L,
                                null,
                                null,
                                null,
                                null,
                                1513275215L
                        ),
                        new Data(
                                "Show HN: This up votes itself",
                                "http://news.ycombinator.com/vote?for=3742902&dir=up&whence=%6e%65%77%65%73%74",
                                "olalonde",
                                83L,
                                null,
                                null,
                                null,
                                null,
                                1332463239L
                        ),
                        new Data(
                                null,
                                null,
                                "olalonde",
                                null,
                                null,
                                "Guacamole – A clientless remote desktop gateway",
                                "https://guacamole.incubator.apache.org/",
                                6547669L,
                                1381763543L
                        ),
                        new Data(
                                null,
                                null,
                                "WisNorCan",
                                1397L,
                                null,
                                "Switch from Chrome to Firefox",
                                "https://www.mozilla.org/en-US/firefox/switch/",
                                null,
                                1559232559L
                        )
                ));
    }

    public static List<String> topArticles(int limit) {
        Response mockResponse = getMockResponse();
        return mockResponse.getData().stream()
                .filter(data -> data.getTitle() != null || data.getStoryTitle() != null)
                .map(data -> {
                    String articleName = "";
                    if (data.getTitle() != null) articleName = data.getTitle();
                    else if (data.getStoryTitle() != null) articleName = data.getStoryTitle();

                    return new Article(articleName, data.getNumComments());
                })
                .sorted((o1, o2) -> {
                    int result = 0;
                    if (o1.getNumComments() == null || o2.getNumComments() == null) return result;

                    result = o2.getNumComments().compareTo(o1.getNumComments());

                    if (result == 0) {
                        if (o1.getName() == null || o2.getName() == null) return result;

                        result = o2.getName().compareTo(o1.getName());
                    }

                    return result;
                })
                .map(Article::getName)
                .limit(limit)
                .collect(Collectors.toList());
    }

}

class Article {
    private String name;
    private Long numComments;

    public Article(String name, Long numComments) {
        this.name = name;
        this.numComments = numComments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumComments() {
        return numComments;
    }

    public void setNumComments(Long numComments) {
        this.numComments = numComments;
    }
}

class Response {
    private int page;
    private int perPage;
    private long total;
    private long totalPages;
    private List<Data> data;

    public Response(int page, int perPage, long total, long totalPages, List<Data> data) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}

class Data {
    private String title;
    private String url;
    private String author;
    private Long numComments;
    private String storyId;
    private String storyTitle;
    private String storyUrl;
    private Long parentId;
    private Long createdAt;

    public Data(String title, String url, String author, Long numComments, String storyId, String storyTitle, String storyUrl, Long parentId, Long createdAt) {
        this.title = title;
        this.url = url;
        this.author = author;
        this.numComments = numComments;
        this.storyId = storyId;
        this.storyTitle = storyTitle;
        this.storyUrl = storyUrl;
        this.parentId = parentId;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNumComments() {
        return numComments;
    }

    public void setNumComments(Long numComments) {
        this.numComments = numComments;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int limit = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<String> result = Result.topArticles(limit);
//
//        bufferedWriter.write(
//                result.stream()
//                        .collect(joining("\n"))
//                        + "\n"
//        );
//
//        bufferedReader.close();
//        bufferedWriter.close();

        System.out.println(Result.topArticles(2));
    }
}
