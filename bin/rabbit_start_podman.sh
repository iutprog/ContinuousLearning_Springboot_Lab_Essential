podman run --rm -d --name rabbit-docker \
  -p 5672:5672 \
  -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=appuser \
  -e RABBITMQ_DEFAULT_PASS=test \
  rabbitmq:4.0.5-management