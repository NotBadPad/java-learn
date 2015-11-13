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
import org.grouplens.lenskit.iterative.IterationCount;
import org.grouplens.lenskit.knn.NeighborhoodSize;
import org.grouplens.lenskit.knn.item.ItemItemScorer;
import org.grouplens.lenskit.knn.user.UserUserItemScorer;
import org.grouplens.lenskit.mf.funksvd.FeatureCount;
import org.grouplens.lenskit.mf.funksvd.FunkSVDUpdateRule;
import org.grouplens.lenskit.mf.funksvd.RuntimeUpdate;
import org.grouplens.lenskit.scored.ScoredId;
import org.grouplens.lenskit.slopeone.DeviationDamping;
import org.grouplens.lenskit.slopeone.SlopeOneItemScorer;
import org.grouplens.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer;
import org.grouplens.lenskit.transform.normalize.MeanCenteringVectorNormalizer;
import org.grouplens.lenskit.transform.normalize.UserVectorNormalizer;
import org.grouplens.lenskit.transform.normalize.VectorNormalizer;

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
    public static void itemToitemTest(){
        LenskitConfiguration config = new LenskitConfiguration();
        config.bind(ItemScorer.class)
                .to(ItemItemScorer.class);

        config.bind(BaselineScorer.class, ItemScorer.class)
                .to(ItemMeanRatingItemScorer.class);

        config.bind(UserVectorNormalizer.class)
                .to(BaselineSubtractingUserVectorNormalizer.class);
        config.set(NeighborhoodSize.class).to(100);

        //https://github.com/lenskit/lenskit-hello/tree/master/data
        config.bind(EventDAO.class).to(new SimpleFileRatingDAO(new File("E:\\workspace\\java\\java-learn\\lenskit-learn\\target\\classes\\training2.txt"), "\t"));


        List<ScoredId> recommendations = null;
        try {
            LenskitRecommenderEngine engine = LenskitRecommenderEngine.build(config);
            LenskitRecommender rec = engine.createRecommender();
            ItemRecommender irec = rec.getItemRecommender();
            recommendations = irec.recommend(3220002, 20);
        } catch (RecommenderBuildException e) {
            e.printStackTrace();
        }

        for (ScoredId sc : recommendations) {
            System.out.println(sc.getId() + "---" + sc.getScore());
        }
    }

    public static void userToUserTest(){
        LenskitConfiguration config = new LenskitConfiguration();
        config.bind(ItemScorer.class)
                .to(UserUserItemScorer.class);

        config.bind(BaselineScorer.class, ItemScorer.class)
                .to(UserMeanItemScorer.class);

        config.bind(UserMeanBaseline.class,ItemScorer.class)
                .to(ItemMeanRatingItemScorer.class);

        config.within(UserVectorNormalizer.class)
                .bind(VectorNormalizer.class)
                .to(MeanCenteringVectorNormalizer.class);
        config.set(NeighborhoodSize.class).to(30);

        //https://github.com/lenskit/lenskit-hello/tree/master/data
        config.bind(EventDAO.class).to(new SimpleFileRatingDAO(new File("E:\\workspace\\java\\java-learn\\lenskit-learn\\target\\classes\\training2.txt"), "\t"));


        List<ScoredId> recommendations = null;
        try {
            LenskitRecommenderEngine engine = LenskitRecommenderEngine.build(config);
            LenskitRecommender rec = engine.createRecommender();
            ItemRecommender irec = rec.getItemRecommender();
            recommendations = irec.recommend(3220002, 20);
        } catch (RecommenderBuildException e) {
            e.printStackTrace();
        }

        for (ScoredId sc : recommendations) {
            System.out.println(sc.getId() + "---" + sc.getScore());
        }
    }

    public static void funkSVDTest(){
        LenskitConfiguration config = new LenskitConfiguration();
        config.bind(ItemScorer.class)
                .to(ItemItemScorer.class);

        config.bind(BaselineScorer.class, ItemScorer.class)
                .to(UserMeanItemScorer.class);

        config.bind(UserMeanBaseline.class, ItemScorer.class)
                .to(ItemMeanRatingItemScorer.class);
        config.bind(RuntimeUpdate.class, FunkSVDUpdateRule.class).to(FunkSVDUpdateRule.class);
        config.set(FeatureCount.class).to(25);
        config.set(IterationCount.class).to(125);

        //https://github.com/lenskit/lenskit-hello/tree/master/data
        config.bind(EventDAO.class).to(new SimpleFileRatingDAO(new File("E:\\workspace\\java\\java-learn\\lenskit-learn\\target\\classes\\training2.txt"), "\t"));


        List<ScoredId> recommendations = null;
        try {
            LenskitRecommenderEngine engine = LenskitRecommenderEngine.build(config);
            LenskitRecommender rec = engine.createRecommender();
            ItemRecommender irec = rec.getItemRecommender();
            recommendations = irec.recommend(3220002, 20);
        } catch (RecommenderBuildException e) {
            e.printStackTrace();
        }

        for (ScoredId sc : recommendations) {
            System.out.println(sc.getId() + "---" + sc.getScore());
        }
    }

    public static void slopeOneTest(){
        LenskitConfiguration config = new LenskitConfiguration();
        config.bind(ItemScorer.class)
                .to(SlopeOneItemScorer.class);

        config.bind(BaselineScorer.class, ItemScorer.class)
                .to(UserMeanItemScorer.class);

        config.bind(UserMeanBaseline.class, ItemScorer.class)
                .to(ItemMeanRatingItemScorer.class);
        config.set(DeviationDamping.class).to(0.0d);

        //https://github.com/lenskit/lenskit-hello/tree/master/data
        config.bind(EventDAO.class).to(new SimpleFileRatingDAO(new File("E:\\workspace\\java\\java-learn\\lenskit-learn\\target\\classes\\training2.txt"), "\t"));


        List<ScoredId> recommendations = null;
        try {
            LenskitRecommenderEngine engine = LenskitRecommenderEngine.build(config);
            LenskitRecommender rec = engine.createRecommender();
            ItemRecommender irec = rec.getItemRecommender();
            recommendations = irec.recommend(3220002, 20);
        } catch (RecommenderBuildException e) {
            e.printStackTrace();
        }

        for (ScoredId sc : recommendations) {
            System.out.println(sc.getId() + "---" + sc.getScore());
        }
    }

    public static void main(String args[]) {
        slopeOneTest();
    }
}
