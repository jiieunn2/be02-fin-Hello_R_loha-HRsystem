FROM nginx:latest
#RUN rm -rf /usr/share/nginx/html/*
ADD ./html/test.docx /usr/share/nginx/html/
RUN rm -rf /etc/nginx/conf.d/*
ADD ./nginx/* /etc/nginx/conf.d/
CMD ["nginx","-g","daemon off;"]
