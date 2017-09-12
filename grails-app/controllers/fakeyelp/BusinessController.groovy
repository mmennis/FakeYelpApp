package fakeyelp

import com.mapr.db.MapRDB
import org.ojai.Document
import org.ojai.Value
import org.ojai.util.Values;
import org.ojai.store.DriverManager
import org.ojai.store.QueryCondition.Op
import org.ojai.store.SortOrder

class BusinessController {

  def getBusinesses(String bizname, String city, String state, Float stars, Integer review_count, String dbtype) {

    println("Parameters: " + params)

    def x = Class.forName("com.mapr.ojai.store.impl.OjaiDriver", true, Thread.currentThread().contextClassLoader)
    def _maprDBConn = DriverManager.getConnection("ojai:mapr:")


    def tableName = null
    if (dbtype.equals("indexed")) {
      tableName = "/tables/business"
    }
    else {
      tableName = "/tables2/business"
    }
    def docStore = _maprDBConn.getStore(tableName)

    def condition = MapRDB.newCondition()
            .and()
            .is("name", Op.EQUAL, bizname)
            .is("city", Op.EQUAL, city)
            .is("state", Op.EQUAL, state)
            .close()
            .build()

    def query = _maprDBConn.newQuery()

    def selectFields = "name,city,state,stars,review_count".split(',')
    for (String field : selectFields) {
      query.select(field)
    }

    query.orderBy("stars", SortOrder.DESC)
    query.where(condition)
    query.limit(20)
    query.build()

    def stream = null
    try {
      stream = docStore.findQuery(query)
      println("Parameters: " + params)
    } catch (Exception e) {
      println("Exception thrown: " + e.getMessage())
      log.error "problem was encountered while running query against table " + tableName, e
      //handleException(e, "problem was encountered while running query against table " + tableName)
    }

    def results = []
    for ( Document record : stream) {
      def businessData = [:]
      for(Map.Entry<String, Value> kv : record) {
        businessData.put("name", record.getString("name"))
        businessData.put("city", record.getString("city"))
        businessData.put("state", record.getString("state"))
        businessData.put("review_count", record.getLong("review_count"))
        businessData.put("stars", record.getFloat("stars"))
      }
      results.add(businessData)
    }
/*
    def results = []
    def biz1 = ["name": "McDonsalds", "city": "Sunnyvale", "state": "CA", "review_count": 3456, "stars": "4.7",
                "categories": ["restaraunt", "fast food", "cheeseburger"]]
    results.add(biz1)
    def biz2 = ["name": "Starbucks", "city": "Sunnyvale", "state": "CA", "review_count": 4125, "stars": "4.9",
              "categories": ["Coffee", "Tea"]]
    results.add(biz2)
    */
    println(results)

    render (template: "/business/businessList", model: [businesses: results] )
  }
}
