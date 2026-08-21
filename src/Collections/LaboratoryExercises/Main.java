package Collections.LaboratoryExercises;

import java.io.*;
import java.util.*;


class Ad implements Comparable<Ad> {
    private String id;
    private String category;
    private double bidValue;
    private double ctr;
    private String content;

    public Ad(String id, String category, double bidValue, double ctr, String content) {
        this.id = id;
        this.category = category;
        this.bidValue = bidValue;
        this.ctr = ctr;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getBidValue() {
        return bidValue;
    }

    public double getCtr() {
        return ctr;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("%s %s (bid=%.2f, ctr=%.2f%%) %s", id, category, bidValue, ctr, content);
    }

    @Override
    public int compareTo(Ad o) {
       int bidCompare = Double.compare(o.bidValue, this.bidValue);
       if(bidCompare != 0)
       {
           return bidCompare;
       }
       return this.id.compareTo(o.id);
    }
}

class AdRequest {
    private String id;
    private String category;
    private double floorBid;
    private String keywords;

    public AdRequest(String id, String category, double floorBid, String keywords) {
        this.id = id;
        this.category = category;
        this.floorBid = floorBid;
        this.keywords = keywords;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getFloorBid() {
        return floorBid;
    }

    public String getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] (floor=%.2f): %s", id, category, floorBid, keywords);
    }
}

class AdNetwork {

    private ArrayList<Ad> ads;

    public AdNetwork() {
        this.ads = new ArrayList<>();
    }

    public void readAds(BufferedReader br) throws IOException {
        while (true) {
            br.mark(10000);
            String line = br.readLine();
            if (line == null) break;
            String[] parts = line.trim().split("\\s+");
            if (parts.length < 5 || !parts[0].startsWith("AD")) {
                br.reset();
                break;
            }
            String id = parts[0];
            String category = parts[1];
            double bidValue = Double.parseDouble(parts[2]);
            double ctr = Double.parseDouble(parts[3]);
            StringBuilder content = new StringBuilder();
            for (int i = 4; i < parts.length; i++) {
                if (i > 4) content.append(" ");
                content.append(parts[i]);
            }
            ads.add(new Ad(id, category, bidValue, ctr, content.toString()));
        }
    }

    private int relevanceScore(Ad ad, AdRequest req) {
        int score = 0;
        if (ad.getCategory().equalsIgnoreCase(req.getCategory())) score += 10;
        String[] adWords = ad.getContent().toLowerCase().split("\\s+");
        String[] keywords = req.getKeywords().toLowerCase().split("\\s+");
        for (String kw : keywords) {
            for (String aw : adWords) {
                if (kw.equals(aw)) score++;
            }
        }
        return score;
    }

    public List<Ad> placeAds(BufferedReader br, int k, PrintWriter pw) throws IOException {
        String line = br.readLine();
        String[] parts = line.trim().split("\\s+");
        String reqId = parts[0];
        String reqCategory = parts[1];
        double floorBid = Double.parseDouble(parts[2]);
        StringBuilder keywords = new StringBuilder();
        for (int i = 3; i < parts.length; i++) {
            if (i > 3) keywords.append(" ");
            keywords.append(parts[i]);
        }
        AdRequest request = new AdRequest(reqId, reqCategory, floorBid, keywords.toString());

        double x = 5.0;
        double y = 100.0;

        List<Ad> eligible = new ArrayList<>();
        for (Ad ad : ads) {
            if (ad.getBidValue() >= request.getFloorBid()) {
                eligible.add(ad);
            }
        }

        eligible.sort((a, b) -> {
            double scoreA = relevanceScore(a, request) + x * a.getBidValue() + y * a.getCtr();
            double scoreB = relevanceScore(b, request) + x * b.getBidValue() + y * b.getCtr();
            return Double.compare(scoreB, scoreA);
        });

        List<Ad> top = new ArrayList<>(eligible.subList(0, Math.min(k, eligible.size())));
        Collections.sort(top);

        pw.println("Top ads for request " + request.getId() + ":");
        for (Ad ad : top) {
            pw.println(ad);
        }
        pw.flush();

        return top;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        AdNetwork network = new AdNetwork();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine().trim());

        if (k == 0) {
            network.readAds(br);
            network.placeAds(br, 1, pw);
        } else if (k == 1) {
            network.readAds(br);
            network.placeAds(br, 3, pw);
        } else {
            network.readAds(br);
            network.placeAds(br, 8, pw);
        }

        pw.flush();
    }
}
