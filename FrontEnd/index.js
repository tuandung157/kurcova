var express = require('express');
var app = express();
var http = require('http').Server(app);

let io = require('socket.io')(http);

io.on('connection', function (socket) {
	console.log('connected'+socket.id);
});
// setup sv express
app.use(express.static('dist'));

http.listen(8888, function () {
	console.log('Listening');
});


//rabbitmq
var amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost', function(error0, connection) {
  if (error0) {
    throw error0;
  }
  connection.createChannel(function(error1, channel) {
    if (error1) {
      throw error1;
    }
    var exchange = 'amq.fanout';

    channel.assertExchange(exchange, 'fanout', {
      durable: true
    });

    channel.assertQueue('', {
      exclusive: true
    }, function(error2, q) {
      if (error2) {
        throw error2;
      }
      console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q.queue);
      channel.bindQueue(q.queue, exchange, '');

      channel.consume(q.queue, function(msg) {
        if(msg.content) {
            console.log(" [x] %s", msg.content.toString());
            io.emit('notification',msg.content.toString());
          }
      }, {
        noAck: true
      });
    });
  });
});


// io.on("connection",function(socket)){
// 	console.log("socket connection with ID " + socket.id);

// }