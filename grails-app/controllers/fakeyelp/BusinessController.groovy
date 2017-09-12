package fakeyelp

import com.mapr.db.MapRDB
import org.ojai.Document
import org.ojai.Value
import org.ojai.util.Values;
import org.ojai.store.DriverManager
import org.ojai.store.QueryCondition.Op
import org.ojai.store.SortOrder

class BusinessController {

  def getBusinesses(String city, String state, Float stars, Integer review_count, Boolean use_index) {

    def bizMap = [:]
    
    def x = Class.forName("com.mapr.ojai.store.impl.OjaiDriver", true, Thread.currentThread().contextClassLoader)
    def _maprDBConn = DriverManager.getConnection("ojai:mapr:")

    def tableName = "/tables/business"
    def docStore = _maprDBConn.getStore(tableName)

    def condition = MapRDB.newCondition()
            .and()
            .is("city", Op.EQUAL, city)
            .is("state", Op.EQUAL, state)
            .is("stars", Op.GREATER_OR_EQUAL, stars)
            .is("review_count", Op.GREATER_OR_EQUAL, review_count)
            .close()
            .build()

    def query = _maprDBConn.newQuery()

    query.select("city,state,stars,review_count,name,categories")
    query.orderBy("stars", SortOrder.DESC).orderBy("review_count", SortOrder.DESC)
    query.where(condition)
    query.limit(20)
    query.build()



    def stream = null
    try {
      docStore.findQuery(query)
      println("Parameters: " + params)
    } catch (Exception e) {
      println("Exception thrown: " + e.getMessage())
      log.error "problem was encountered while running query against table " + tableName, e
      //handleException(e, "problem was encountered while running query against table " + tableName)
    }
    println("The docStore returned a " + stream.getClass().toString())
    for ( Document record : stream) {
      for(Map.Entry<String, Value> kv : record) {
        bizMap.put(kv.getKey(), Values.asJsonString(kv.getValue()))
      }
    }

    println("Parameters: " + params)

    bizMap['name'] = 'Starbucks'
    bizMap['city'] = 'Mountain View'
    bizMap['state'] = 'CA'
    bizMap['stars'] = 2.0
    bizMap['review_count'] = 4500


    def answer = [:]
    if (use_index) {
      answer['name'] = 'McDonalds'
    } else {
      answer['name'] = 'Caffolas'
    }

    answer['city'] = city
    answer['state'] = state
    answer['stars'] = stars
    answer['review_count'] = review_count

    def arr = [bizMap, answer]

    render (template: "/business/businessList", model: [businesses: arr] )
  }
}
