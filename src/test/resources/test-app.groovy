set "public", "public"

get('/') {
   render('index.html')
}

get('/home') {
   redirect('/')
 }