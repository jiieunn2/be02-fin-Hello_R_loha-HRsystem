# 이미지 다운로드에 문제가 있을 경우, 직접 nginx 이미지를 다운로드합니다.
FROM nginx:latest

ADD ./dist/css /usr/share/nginx/html/css
ADD ./dist/js /usr/share/nginx/html/js
ADD ./dist/static /usr/share/nginx/html/static
ADD ./dist/static/images /usr/share/nginx/html/static/images
RUN rm -rf /usr/share/nginx/html/index.html
ADD ./dist/index.html /usr/share/nginx/html/index.html
RUN rm -rf /etc/nginx/conf.d/default.conf
ADD ./nginx/default.conf /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]
