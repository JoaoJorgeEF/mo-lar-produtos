package br.com.molar.produtos.mensageria;

import br.com.molar.produtos.entities.base.BaseEntity;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class Producer {
    public static void enfileirar(Long id, Class<? extends BaseEntity> classType) throws Exception {
        if (id <= 0) throw new Exception("ID não existente");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");

        String NOME_FILA = nomeDaFila(classType);
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            //Declaracao da fila. Se nao existir ainda no queue manager, serah criada. Se jah existir, e foi criada com
            // os mesmos parametros, pega a referencia da fila. Se foi criada com parametros diferentes, lanca excecao
            channel.queueDeclare(NOME_FILA, false, false, false, null);
            String mensagem = id.toString();
            //publica uma mensagem na fila
            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes());
            System.out.println("Mensagem enviada, objeto do tipo("+ classType.getName() +") de ID("+ id +")");
        }

    }

    private static String nomeDaFila(Class<? extends BaseEntity> classType){
        String className = classType.getName();
        return className.substring(className.lastIndexOf('.')+1);
    }
}
