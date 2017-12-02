package services

import javax.inject.Inject

import akka.actor.{Actor, ActorSystem, Props}
import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client._
import play.Logger
import services.RabbitMQConfig.RABBITMQ_QUEUE
import services.RabbitMQConnection._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class Sender @Inject()(actorSystem:ActorSystem)(implicit exec : ExecutionContext){
  Logger.debug("Sender instantiating")

  val connection: Connection = getConnection

  val sendingChannel: Channel = connection.createChannel()

  sendingChannel.queueDeclare(RABBITMQ_QUEUE,false,false,false,null)

  val callback1: (String) => Unit = (x:String) => Logger.info("Received on queue callback 1: " + x)

  setupListener(connection.createChannel(),RABBITMQ_QUEUE,callback1)

  val callback2: (String) => Unit = (x:String) => Logger.info("Received on queue callback 2: " + x)

  setupListener(connection.createChannel(),RABBITMQ_QUEUE,callback2)

  actorSystem.scheduler.schedule(2.seconds, 1.seconds
    , actorSystem.actorOf(Props(
      new SendingActor( channel = sendingChannel,
        queue = RABBITMQ_QUEUE)))
    ,"MSG to Queue")

  private def setupListener(receivingChannel: Channel, queue:String, f:(String) => Any){
    actorSystem.scheduler.scheduleOnce(2.seconds,
      actorSystem.actorOf(Props(new ListeningActor(receivingChannel,queue,f))),"")

  }
  class SendingActor(channel: Channel, queue: String) extends Actor{
    def receive: PartialFunction[Any,Unit] = {
      case some : String =>
        val msg = some + " : " + System.currentTimeMillis()
        channel.basicPublish("",queue, null, msg.getBytes())
        Logger.info(msg)
      case _ =>
    }
  }
  class ListeningActor(channel: Channel, queue: String, f: (String) => Any) extends Actor{
     def receive: PartialFunction[Any,Unit] = {
       case _=> startReceiving()
     }
    def startReceiving(): Unit = {
      val consumer = new DefaultConsumer(channel){
        override def handleDelivery(consumerTag: String, envelope: Envelope, properties: BasicProperties, body: Array[Byte]): Unit = {

          val msg = new String(body)

          context.actorOf(Props(new Actor{
            def receive:PartialFunction[Any,Unit] = {
              case some: String => f(some)
            }
          })) ! msg

          super.handleDelivery(consumerTag,envelope,properties,body)


        }
      }
    }
  }

}
