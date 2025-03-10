#!/bin/bash
echo "Waiting for SQL Server to start..."
sleep 15s

until /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Password789" -Q "SELECT 1" -N o; do
  echo "⚠️ SQL Server is not ready yet, waiting another 5 seconds..."
  sleep 5
done

if /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Password789" -Q "SELECT name FROM sys.databases WHERE name = 'pis'" -N o | grep -q "pis"; then
  echo "Database 'pis' already exists, skipping script execution."
else
  echo "Database 'pis' does not exist, creating..."
  /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Password789" -Q "CREATE DATABASE pis" -N o
  sleep 5

  echo "Running initialization scripts..."
  /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Password789" -d pis -i /docker-entrypoint-initdb.d/init.sql -N o
  /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Password789" -d pis -i /docker-entrypoint-initdb.d/data.sql -N o
  echo "Database 'pis' has been successfully initialized!"
fi
