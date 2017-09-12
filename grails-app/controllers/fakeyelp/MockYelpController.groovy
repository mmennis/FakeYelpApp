package fakeyelp

class MockYelpController {

  def cities = "Boisbriand,Bonnyrigg,Braddock,Brentwood,Breslau,Bridgeville,Brookline,Brossard,Bruchsal,Bruntsfield,Clark,Clover,Columbus,Concord,Conestogo,Coolidge,Dallas,Dane,DeForest,Deforest,Ettlingen,Fabreville,Fitchburg,Fitchburgh,Florence,Forchheim,Ingram,Inverkeithing,Jockgrim,Kahnawake,Karlsbad,Karlsruhe,Kirkland,Kitchener,LaSalle,Lachenaie,Lachine,LasVegas,Lasalle,Lasswade,Laval,Laveen,Lawrenceville,Leith,Litchfield,Loanhead,Locust,London,Longueuil,MADISON,MESA,MMRP,Madison,Maricopa,Mascouche,Mathews,Mattews,Matthews,McAdenville,McFarland,McKeesport,Mcfarland,Mesa,Middleton,Midlothian,Millvale,Mirabel,Monona,Monroe,Mont-Royal,Montgomery,Montral,Montreal,Montreal-Est,Montreal-Nord,Montreal-Ouest,Montreal-West,Morristown,Munhall,Musselburgh,Nellis,Newbridge,Newington,Oakland,Oregon,Outremont,PEORIA,PITTSBURGH,Paradise,Payson,Penicuik,Peopria,Peoria,Pfinztal,Pheonix,Phoe,Phoenix,Phoneix,Pierrefonds,Pineville,Pittsburg,Pittsburgh,Pointe-Aux-Trembles,Pointe-Claire,Portobello,Queensferry,Rankin,Ratho,Repentigny,Rheinstetten,Roosevelt,Rosemere,Roslin,Roxboro,SCOTTSDALE,Saint-Eustache,Saint-Henri,Saint-Hubert,Saint-Lambert,Saint-Laurent,Saint-Leonard,Saint-laurent,Sainte-Anne-De-Bellevue,Sainte-Anne-de-Bellevue,Sainte-Anne-des-Plaines,Sainte-Genevieve,Sainte-Therese,Savoy,Scotesdale,Scotland,Scottdale,Scottsdale,Sedona,Shadyside,Sharpsburg,San Jose,Summerlin,Urbana,Verdun,Verona,Westmount,Whitehall,Whitney,Wickenburg,Wilkinsburg,Windsor,Wittmann,Youngtown,glendale,phoenix,scottsdale".split(',')
  def states = "AK,AL,AZ,BW,CA,EDH,ELN,FIF,FL,HAM,IL,KHL,MLN,MN,NC,NM,NTH,NV,NW,ON,PA,QC,RP,SC,SCB,TAM,TX,WI,XGL".split(',')
  def stars = "5.0,4.0,3.0,2.0,1.0".split(',')


  def index() {
    render(view: "index", model: [cities: cities, states: states, stars: stars])
  }
}
