import urllib.request
from bs4 import BeautifulSoup

if __name__ == "__main__":
    req = urllib.request.Request("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%84%B1%EC%9D%B5&oquery=%EC%84%B1%EC%9D%B5&tqi=TkpeMwpySowsstSKS%2Fwssssssjl-408860");
    data = urllib.request.urlopen(req).read()

    bs = BeautifulSoup(data, 'html.parser')

    relkw = bs.find(id = 'nx_related_keywords').find_all('li')

    ntp = bs.find(id = 'nxfr_htp').find_all('li')

    hotkw = bs.find(id = 'nxfr_ugrank').find_all('li')    

    print("연관검색어 목록\n")
    for i in relkw :
        try :
            relkwtext = i.get_text()
            print(relkwtext)

        except UnicodeEncodeError:
            print("\nError\n")


    print('\n뉴스토픽목록\n')    
    for i in ntp:
        try :
            ntptext = i.next_element()
            print (ntptext)
            
        except UnicodeEncodeError:
            print("\nError\n")

    print('\n싱글녀 인기검색어 목록\n')

    for i in hotkw:
        try :
            
            hotkwtext = i.get_text()
            print(hotkwtext)
            
        except UnicodeEncodeError:
            print("\nError\n")
