package com.wojciechwaldon.simplesearch.infrastructure;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohamed Guendouz
 */
public class Test {
    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }

    public static void main(String[] args) {

        List<String> doc1 = Arrays.asList("the", "brown", "fox", "jumped", "over", "the", "brown", "dog");
        List<String> doc2 = Arrays.asList("the", "lazdy", "brown", "dog", "sat", "in", "the", "corner");
        List<String> doc3 = Arrays.asList("the", "red", "fox", "beat", "the", "lazy", "dog");
        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

        Test calculator = new Test();
        double tfidf = calculator.tfIdf(doc3, documents, "fox");
        System.out.println("TF-IDF = " + tfidf);


    }


}
