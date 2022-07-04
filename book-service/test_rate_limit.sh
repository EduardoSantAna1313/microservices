while :; do
    sleep 0.1;
    curl http://localhost:8765/book-service/ratelimit
    printf "\n"
done