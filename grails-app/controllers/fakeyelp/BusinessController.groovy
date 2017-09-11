package fakeyelp

import com.mapr.db.MapRDB
import com.mapr.ojai.store.impl.OjaiOptions
import org.ojai.Document
import org.ojai.Value
import org.ojai.util.Values;
import org.ojai.store.DriverManager
import org.ojai.store.QueryCondition.Op
import org.ojai.store.SortOrder

class BusinessController {



  def getBusinesses(String city, String state, Float stars, Integer review_count) {

    def x = Class.forName("com.mapr.ojai.store.impl.OjaiDriver", true, Thread.currentThread().contextClassLoader)
    def _maprDBConn = DriverManager.getConnection("ojai:mapr:")

    def tableName = "/tables/business"
    def docStore = _maprDBConn.getStore(tableName)

    println("Parameters: " + params)

    def condition = MapRDB.newCondition()
            .is("city", Op.EQUAL, city)
            .is("state", Op.EQUAL, state)
            .is("stars", Op.GREATER_OR_EQUAL, stars)
            .is("review_count", Op.GREATER_OR_EQUAL, review_count)
            .build()

    def query = _maprDBConn.newQuery()
    def selectedFields = new ArrayList<String>()
    selectedFields.add("city")
    selectedFields.add("state")
    selectedFields.add("stars")
    selectedFields.add("review_count")
    selectedFields.add("name")
    selectedFields.add("categories")

    query.select(selectedFields)
    query.orderBy("stars", SortOrder.DESC).orderBy("review_count", SortOrder.DESC)
    query.where(condition)
    query.limit(100)
    query.build()

    def bizMap = [:]

    def stream = {
      try {
        docStore.findQuery(query)
      } catch (Exception e) {
        handleException(e, "problem was encountered while running query against table " + tableName)
      }
    }


    for ( Document record : stream) {
      for(Map.Entry<String, Value> kv : record) {
        bizMap.put(kv.getKey(), Values.asJsonString(kv.getValue()))
      }
    }



    bizMap['name'] = 'Starbucks'
    bizMap['city'] = 'Mountain View'
    bizMap['state'] = 'CA'
    bizMap['stars'] = 2.0
    bizMap['review_count'] = 4500

    def answer = [:]
    answer['name'] = 'McDonalds'
    answer['city'] = city
    answer['state'] = state
    answer['stars'] = stars
    answer['review_count'] = review_count

    def arr = [bizMap, answer]

    render (template: "/business/businessList", model: [businesses: arr] )
  }
}
