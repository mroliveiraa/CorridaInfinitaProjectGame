# Usa a imagem oficial do MySQL
FROM mysql:8.0

# Define variáveis de ambiente para configurar o banco
ENV MYSQL_DATABASE=programadoresvsrobos
ENV MYSQL_USER=mateus
ENV MYSQL_PASSWORD=senhaSegura
ENV MYSQL_ROOT_PASSWORD=root123

# Expõe a porta padrão do MySQL
EXPOSE 3306
