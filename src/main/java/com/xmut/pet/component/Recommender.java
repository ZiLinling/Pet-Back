//package com.xmut.pet.component;
//
//
//
//import com.xmut.pet.entity.Comment;
//import com.xmut.pet.service.CommentService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FileUtils;
//import org.apache.mahout.cf.taste.common.Refreshable;
//import org.apache.mahout.cf.taste.common.TasteException;
//import org.apache.mahout.cf.taste.eval.IRStatistics;
//import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
//import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
//import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
//import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
//import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
//import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
//import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
//import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
//import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
//import org.apache.mahout.cf.taste.impl.model.GenericPreference;
//import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
//import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
//import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
//import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
//import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
//import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.*;
//import org.apache.mahout.cf.taste.model.DataModel;
//import org.apache.mahout.cf.taste.model.PreferenceArray;
//import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
//import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
//import org.apache.mahout.cf.taste.similarity.UserSimilarity;
//import org.springframework.lang.NonNull;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Slf4j
//public class Recommender {
//    private CommentService commentService;
//
//
//    public void test() throws IOException, TasteException {
//        List<Comment> comments =commentService.list();
//        // 第一步，定义数据模型
//        DataModel dataModel = buildJdbcDataModel(comments);
//
//        // 第二步，定义相识度，这里使用的欧几里得
//        UserSimilarity userSimilarity = new EuclideanDistanceSimilarity(dataModel);
//
//        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(4, userSimilarity, dataModel);
//        long[] longs = userNeighborhood.getUserNeighborhood(1);
//        for (long aLong : longs) {
//            System.out.println(aLong);
//        }
//
//
//        GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
//
//        LongPrimitiveIterator userIDs = dataModel.getUserIDs();
//        while (userIDs.hasNext()) {
//            Long userId = userIDs.next();
//            List<RecommendedItem> recommendedItemList = recommender.recommend(userId, 4);
//
//            StringBuffer sb = new StringBuffer();
//            for (RecommendedItem item : recommendedItemList) {
//                sb.append(item.getItemID() + "|"+item.getValue()+",");
//            }
//
//            System.out.println(userId + "-->" + sb);
//        }
//    }
//    /**
//     * dataModel 有两种结构：
//     * GenericDataModel: 用户ID，物品ID，用户对物品的打分(UserID,ItemID,PreferenceValue)
//     * GenericBooleanPrefDataModel: 用户ID，物品ID (UserID,ItemID)，这种方式表达用户是否浏览过该物品，但并未对物品进行打分。
//     * 因为系统需要根据用户行为或评分进行推荐所以使用GenericDataModel
//     * @param preferenceList 用户行为或评分集合
//     * @return DataModel
//     */
//    private static DataModel buildJdbcDataModel(List<Comment> preferenceList) {
//        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();
//        Map<Integer, List<Comment>> map = preferenceList.stream().collect(Collectors.groupingBy(Comment::getUserId));
//
//        Collection<List<Comment>> list = map.values();
//        for (List<Comment> comments : list) {
//            GenericPreference[] array = new GenericPreference[comments.size()];
//            for (int i = 0; i < comments.size(); i++) {
//                Comment comment = comments.get(i);
//                GenericPreference item = new GenericPreference(comment.getUserId(), comment.getGoodsId(), ((Double)comment.getLevel()).floatValue());
//                array[i] = item;
//            }
//            fastByIdMap.put(array[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(array)));
//        }
//        return new GenericDataModel(fastByIdMap);
//    }
//
//
//}
//
