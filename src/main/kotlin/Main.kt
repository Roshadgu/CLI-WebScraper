import it.skrape.core.htmlDocument
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape

fun getDocumentByUrl(urlToScrape: String) = skrape(BrowserFetcher) { // <--- pass BrowserFetcher to include rendered JS
  request { url = urlToScrape }
  response { htmlDocument { this } }
}


fun main() {
  val links: Unit = skrape(BrowserFetcher) {
    request {
      url = "https://kotlinlang.org/docs/reference/"
    }
    extract {
      htmlDocument {
        a {
          findAll {
            eachLink
          }
        }

      }
    }
  }

  links.forEach { (text, link) -> println("$text --> $link")}
}