#!/bin/bash
set -e  # Dừng script nếu có lỗi

echo "🚀 Waiting for PostgreSQL to start..."
until pg_isready -h localhost -U "$POSTGRES_USER"; do
  echo "⚠️ PostgreSQL is not ready yet, waiting another 5 seconds..."
  sleep 5
done

echo "✅ PostgreSQL is ready!"

# Kiểm tra xem database 'pis' đã tồn tại chưa
DB_EXISTS=$(psql -U "$POSTGRES_USER" -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname='pis'")

if [ "$DB_EXISTS" == "1" ]; then
  echo "✅ Database 'pis' already exists, skipping script execution."
else
  echo "🚀 Database 'pis' does not exist, creating..."
  psql -U "$POSTGRES_USER" -d postgres -c "CREATE DATABASE pis;"
  sleep 3

  echo "🚀 Running initialization scripts..."

  if [ -f "/custom-scripts/init.sql" ]; then
    echo "▶ Running init.sql..."
    psql -U "$POSTGRES_USER" -d pis -f /custom-scripts/init.sql
  else
    echo "⚠️ Warning: init.sql not found, skipping."
  fi

  if [ -f "/custom-scripts/data.sql" ]; then
    echo "▶ Running data.sql..."
    psql -U "$POSTGRES_USER" -d pis -f /custom-scripts/data.sql
  else
    echo "⚠️ Warning: data.sql not found, skipping."
  fi

  echo "✅ Database 'pis' has been successfully initialized!"
fi
