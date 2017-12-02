package services

import com.rabbitmq.client.{Connection, ConnectionFactory}
import services.RabbitMQConfig._
object RabbitMQConnection {
  private val connection: Connection = null

  /*
    Returns a connection if one doesn't exist. Else create a new one
   */

  def getConnection():Connection = {
    connection match{
      case null => {
        val factory  = new ConnectionFactory()
        factory.setHost(RABBITMQ_HOST)
        factory.newConnection()
      }
      case _ => connection
    }
  }

}
