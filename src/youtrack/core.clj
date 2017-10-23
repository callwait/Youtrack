(ns youtrack.core
  (:import java.util.Base64)
  (:require
    [clj-http.client :as client]
    [cheshire.core :refer :all])
  (:gen-class))


(def conf_cookies  {:url      "https://you.myjetbrains.com/rest/user/login"
                    :login    ""  ; email
                    :pwd      ""  ; passwords
                    })


(defn get-cookies []
      "function auth by login & password; return cookies"
      (let [response (client/post (str (:url conf_cookies))
                                  {:content-type :application/x-www-form-urlencoded
                                   :body         (str "login=" (:login conf_cookies) "&password=" (:pwd conf_cookies))})]
        (println :cookies response)
        (str (:cookies response))))


(def cookies (get-cookies))


(defn fetch-page-by-cookies [url]
      "download page by cookies"
      (:body (client/get url {:cookies cookies})))










(def conf_token  {:url      "https://hub.myjetbrains.com/hub/api/rest/oauth2/token"
                  :service  ""
                  :scope    ""
                  :secret   ""
                  })


(defn b64 [to-encode]
      "Base65 encoding"
      (.encodeToString (Base64/getEncoder) (.getBytes to-encode)))


(defn get-token []
      "Get token function"
      (let [response (client/post (:url conf) {:headers {"authorization" (str "Basic " (b64 (str (:service conf_token) ":" (:secret conf_token))))}
                                                  :body (str "grant_type=client_credentials&scope=" (:scope conf_token))
                                                  :content-type "application/x-www-form-urlencoded"})]
        (println (:body response))
        (parse-string (:body response) true)))


(def token (:access_token (get-token)))


(defn fetch-page-by-token [url]
      "download page by token"
      (:body (client/get url {:headers {"Authorization" (str "Bearer " (str token))}})))