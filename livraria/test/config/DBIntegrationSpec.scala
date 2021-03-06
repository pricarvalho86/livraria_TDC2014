package config

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import scala.collection.mutable.MutableList

@RunWith(classOf[JUnitRunner])
class DBIntegrationSpec extends Specification with DBIntegration {
	
	"H2 Database in Memory" should {
		
		"criar todas as tabelas no H2 de acordo com as classes modelos(dao.models.*) existentes" in {
			db.withSession {
				implicit session =>
					val result = db.createConnection.prepareStatement("show tables from livraria").executeQuery()
					val tabelas = new MutableList[String]()
					while (result.next) {
						tabelas += result.getString(1)
					}
					tabelas.size must be equalTo tableList.size
			}
		}

		"buscar todos os livros cadastrados como massa padrao no H2 em mémoria" in {
			db.withSession {
				implicit session =>
					val result = db.createConnection.prepareStatement("select * from livraria.livro").executeQuery()
					val tabelas = new MutableList[String]()
					while (result.next) {
						tabelas += result.getString(1)
					}
					tabelas.size must be equalTo 7
			}
		}

	}

}