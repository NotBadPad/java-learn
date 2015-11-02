package com.gj.lenskit.demo;

import org.grouplens.lenskit.ItemRecommender;
import org.grouplens.lenskit.ItemScorer;
import org.grouplens.lenskit.RatingPredictor;
import org.grouplens.lenskit.RecommenderBuildException;
import org.grouplens.lenskit.baseline.BaselineScorer;
import org.grouplens.lenskit.baseline.ItemMeanRatingItemScorer;
import org.grouplens.lenskit.baseline.UserMeanBaseline;
import org.grouplens.lenskit.baseline.UserMeanItemScorer;
import org.grouplens.lenskit.core.LenskitConfiguration;
import org.grouplens.lenskit.core.LenskitRecommender;
import org.grouplens.lenskit.core.LenskitRecommenderEngine;
import org.grouplens.lenskit.data.dao.EventDAO;
import org.grouplens.lenskit.data.dao.SimpleFileRatingDAO;
import org.grouplens.lenskit.knn.item.ItemItemScorer;
import org.grouplens.lenskit.scored.ScoredId;
import org.grouplens.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer;
import org.grouplens.lenskit.transform.normalize.UserVectorNormalizer;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guojing
 * Date: 15-11-2
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class LensKitDemo {

    public static void main(String args[]) {
        LenskitConfiguration config = new LenskitConfiguration();
        config.bind(ItemScorer.class)
                .to(ItemItemScorer.class);

        config.bind(BaselineScorer.class, ItemScorer.class)
                .to(UserMeanItemScorer.class);

        config.bind(UserMeanBaseline.class, ItemScorer.class)
                .to(ItemMeanRatingItemScorer.class);
        config.bind(UserVectorNormalizer.class)
                .to(BaselineSubtractingUserVectorNormalizer.class);

        //https://github.com/lenskit/lenskit-hello/tree/master/data
        config.bind(EventDAO.class).to(new SimpleFileRatingDAO(new File("E:\\workspace\\java\\java-learn\\lenskit-learn\\target\\classes\\ratings.csv"), ","));


        List<ScoredId> recommendations = null;
        double score = 0;
        try {
            LenskitRecommenderEngine engine = LenskitRecommenderEngine.build(config);
            LenskitRecommender rec = engine.createRecommender();
            ItemRecommender irec = rec.getItemRecommender();
            recommendations = irec.recommend(42, 10);

            RatingPredictor pred = rec.getRatingPredictor();
            score = pred.predict(42, 17);
        } catch (RecommenderBuildException e) {
            e.printStackTrace();
        }

        for (ScoredId sc : recommendations) {
            System.out.println(sc.getId() + "---" + sc.getScore());
        }

        System.out.println(score + "");
    }
}
